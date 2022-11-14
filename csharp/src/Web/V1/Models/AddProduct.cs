using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Text.Json.Serialization;
using SomeBasicFileStoreApp.Core;
using SomeBasicFileStoreApp.Core.Commands;

namespace Web.V1.Models;

[JsonPolymorphic(UnknownDerivedTypeHandling = JsonUnknownDerivedTypeHandling.FallBackToBaseType)]
[JsonDerivedType(typeof(AddProduct2), typeDiscriminator: "product2")]
public class AddProduct
{
    /// 
    public virtual Command ToCommand()
    {
        return new AddProductCommand
        {
            Id = 0,
            Name= Name,
            Cost=Cost
        };
    }

    /// <summary>
    /// 
    /// </summary>
    [Range(0.1, 1_000_000.0)]
    [DataType(DataType.Currency)]
    [Required]
    public float Cost { get; set; }
    /// <summary>
    /// 
    /// </summary>
    [StringLength(60, MinimumLength = 3)]
    [Required]
    public string Name { get; set; }

}

public class AddProduct2 : AddProduct
{
    public override Command ToCommand()
    {
        return new AddProductCommand
        {
            Id = 0,
            Name= Name,
            Cost=Cost,
            Properties = Properties
        };
    }
    /// <summary>
    /// 
    /// </summary>
    [Required]
    public IDictionary<ProductProperty,string> Properties { get; set; } 
}

