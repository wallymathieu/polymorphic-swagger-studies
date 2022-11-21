using System;
using System.Linq;
using System.Text.Json;
using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using SomeBasicFileStoreApp.Core;
using SomeBasicFileStoreApp.Core.Commands;
using SomeBasicFileStoreApp.Core.Infrastructure;
using JsonAppendToFile = SomeBasicFileStoreApp.Core.Infrastructure.Json.AppendToFile;
using ProtoAppendToFile = SomeBasicFileStoreApp.Core.Infrastructure.ProtoBuf.AppendToFile;

namespace Web
{
    public class Startup
    {
        private readonly SwaggerConfig _swagger;

        public Startup(IConfiguration configuration)
        {
            Configuration = configuration;
            _swagger = new SwaggerConfig();
        }

        public IConfiguration Configuration { get; }

        /// This method gets called by the runtime. Use this method to add services to the container.
        public void ConfigureServices(IServiceCollection services)
        {
            _swagger.ConfigureServices(services);
            
            services.AddSingleton<PersistCommandsHandler>();
            services.AddSingleton<IRepository, Repository>();
            services.AddSingleton<Func<DateTime>>(_ => () => DateTime.UtcNow);
            var jsonFile = Configuration["JSON_FILE"];
            if (!string.IsNullOrEmpty(jsonFile))
            {
                services.AddSingleton<IAppendBatch, JsonAppendToFile>(_ => new JsonAppendToFile(jsonFile));
            }

            var protoFile = Configuration["PROTO_FILE"];
            if (!string.IsNullOrEmpty(protoFile))
            {
                services.AddSingleton<IAppendBatch, ProtoAppendToFile>(_ => new ProtoAppendToFile(protoFile));
            }

            services.AddControllers().AddApplicationPart(typeof(Startup).Assembly).AddJsonOptions(opts =>
            {
                opts.JsonSerializerOptions.Converters.Add(new JsonStringEnumConverter());
            });

            services.Configure<ApiBehaviorOptions>(options =>
            {
                options.InvalidModelStateResponseFactory = actionContext =>
                {
                    var errors = actionContext.ModelState
                        .Where(e => e.Value?.Errors.Count > 0)
                        .ToDictionary(
                            e => e.Key.FirstLetterToLower(),
                            e => string.Join(", ",
                                e.Value?.Errors.Select(m => m.ErrorMessage) ?? Enumerable.Empty<string>()));

                    return new BadRequestObjectResult(errors);
                };
            });
        }

        // This method gets called by the runtime. Use this method to configure the HTTP request pipeline.
        public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
        {
            if (env.IsDevelopment())
            {
                app.UseDeveloperExceptionPage();
            }
            else
            {
                app.UseHsts();
            }

            app.UseStaticFiles();
            _swagger.Configure(app, env);
            app.UseHttpsRedirection();
            app.UseRouting();

            //app.UseAuthorization();

            app.UseEndpoints(endpoints =>
            {
                endpoints.MapControllerRoute(
                    name: "default",
                    pattern: "{controller=Home}/{action=Index}/{id?}");
            });
            var lifetime = app.ApplicationServices.GetRequiredService<IHostApplicationLifetime>();
            lifetime.ApplicationStarted.Register(() =>
            {
                using var context = app.ApplicationServices.CreateScope();
                var persist = context.ServiceProvider
                    .GetRequiredService<PersistCommandsHandler>();
                persist.Start();
                var repository = context.ServiceProvider
                    .GetRequiredService<IRepository>();
                var commands = persist.YieldStored()?.Result;
                if (commands != null)
                    foreach (var command in commands)
                    {
                        command.Run(repository);
                    }
            });
        }
    }
}
