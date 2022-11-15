using System.Collections.Generic;
using SomeBasicFileStoreApp.Core;

namespace Web.V1.Models
{
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
                :new ProductModelV1
                {
                    Name = arg.Name,
                    Id = arg.Id,
                    Cost = arg.Cost,
                };
        }
    }
    public class ProductModelV1: ProductModel
    {
    }
    public class ProductModelV2: ProductModel
    {
        public IDictionary<ProductProperty,string> Properties { get; set; }

    }
}