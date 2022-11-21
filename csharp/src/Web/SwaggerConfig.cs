using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Reflection;
using System.Text.Json.Serialization;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Hosting;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.OpenApi.Models;
using Swashbuckle.AspNetCore.Filters;

namespace Web;

class SwaggerConfig
{
    ///
    public void Configure(IApplicationBuilder app, IWebHostEnvironment env)
    {
        // Enable middleware to serve generated Swagger as a JSON endpoint
        app.UseSwagger(c => { c.RouteTemplate = "swagger/{documentName}/swagger.json"; });

        app.UseReDoc(c =>
        {
            c.RoutePrefix = "docs";
            c.SpecUrl("/swagger/v1/swagger.json");
        });
    }

    ///
    public virtual void ConfigureServices(IServiceCollection services)
    {
        services.AddSwaggerExamplesFromAssemblyOf<Startup>();
        services.AddSwaggerGen(c => {
            c.ExampleFilters();
        });

        services.ConfigureSwaggerGen(options =>
        {
            var webAssembly = typeof(Startup).GetTypeInfo().Assembly;
            var informationalVersion =
                (webAssembly.GetCustomAttributes(typeof(AssemblyInformationalVersionAttribute))
                    as AssemblyInformationalVersionAttribute[])?.First()?.InformationalVersion;

            options.SwaggerDoc("v1", new OpenApiInfo
            {
                Version = informationalVersion ?? "dev",
                Title = "API",
                Description = "Some API",
                Contact = new OpenApiContact
                {
                    Name = "Dev", Email = "developers@somecompany.com", Url = new Uri("https://somecompany.com")
                }
            });
            options.UseAllOfForInheritance();
            options.UseOneOfForPolymorphism();
            options.SelectDiscriminatorNameUsing((baseType) =>
                baseType.GetCustomAttribute<JsonPolymorphicAttribute>()
                    ?.TypeDiscriminatorPropertyName?? "$type");
            options.SelectDiscriminatorValueUsing((subType) => 
                subType.GetCustomAttributes<JsonDerivedTypeAttribute>()
                    .SingleOrDefault(t=>t.DerivedType==subType)
                        ?.TypeDiscriminator?.ToString() ?? subType.Name);
            options.SelectSubTypesUsing(GetSubTypes);
            //Set the comments path for the swagger json and ui.
            var xmlPath = Path.Combine(Directory.GetParent(webAssembly.Location).ToString(),
                webAssembly.GetName().Name + ".xml");
            if (File.Exists(xmlPath))
                options.IncludeXmlComments(xmlPath);
            else
                Console.Error.WriteLine($"Could not find xml {xmlPath}");
        });
    }

    private static IEnumerable<Type> GetSubTypes(Type baseType)
    {
        return typeof(Program).Assembly.GetTypes()
            //.Union(typeof(Product).Assembly.GetTypes())
            .Where(type => type.IsSubclassOf(baseType));
    }
}