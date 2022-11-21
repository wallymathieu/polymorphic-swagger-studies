using System.Collections.Generic;
using System.Text.Json.Serialization;
using SomeBasicFileStoreApp.Core;

namespace Web.V1.Models
{
    [JsonPolymorphic(UnknownDerivedTypeHandling = JsonUnknownDerivedTypeHandling.FallBackToBaseType, 
        TypeDiscriminatorPropertyName = "version")]
    [JsonDerivedType(typeof(ProductModelV2), typeDiscriminator: "v2")]
    public class ProductModel
    {
        public int Id { get; set; }

        public float Cost { get; set; }

        public string Name { get; set; }

        public static ProductModel Map(Product arg)
        {
            return arg.Properties!=null
                ? new ProductModelV2
                {
                    Name = arg.Name,
                    Id = arg.Id,
                    Cost = arg.Cost,
                    Properties = arg.Properties,
                }
                :new ProductModel
                {
                    Name = arg.Name,
                    Id = arg.Id,
                    Cost = arg.Cost,
                };
        }
    }
    public class ProductModelV2: ProductModel
    {
        public IDictionary<ProductProperty,string> Properties { get; set; }

    }
}