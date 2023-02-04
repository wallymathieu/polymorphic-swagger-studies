package se.gewalli.polymorphic_swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.math.BigInteger;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import se.gewalli.polymorphic_swagger.model.ProductModel;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * ProductModel
 */

@JsonIgnoreProperties(
  value = "version", // ignore manually set version, it will be automatically generated by Jackson during serialization
  allowSetters = true // allows the version to be set during deserialization
)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "version", visible = true)
@JsonSubTypes({
  @JsonSubTypes.Type(value = ProductModel.class, name = "ProductModel"),
  @JsonSubTypes.Type(value = ProductModelV2.class, name = "ProductModelV2")
})

public class ProductModel {

  @JsonProperty("version")
  private String version;

  @JsonProperty("id")
  private BigInteger id;

  @JsonProperty("cost")
  private Float cost;

  @JsonProperty("name")
  private JsonNullable<String> name = JsonNullable.undefined();

  public ProductModel version(String version) {
    this.version = version;
    return this;
  }

  /**
   * Get version
   * @return version
  */
  @NotNull 
  @Schema(name = "version", required = true)
  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public ProductModel id(BigInteger id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public ProductModel cost(Float cost) {
    this.cost = cost;
    return this;
  }

  /**
   * Get cost
   * @return cost
  */
  
  @Schema(name = "cost", required = false)
  public Float getCost() {
    return cost;
  }

  public void setCost(Float cost) {
    this.cost = cost;
  }

  public ProductModel name(String name) {
    this.name = JsonNullable.of(name);
    return this;
  }

  /**
   * Get name
   * @return name
  */
  
  @Schema(name = "name", required = false)
  public JsonNullable<String> getName() {
    return name;
  }

  public void setName(JsonNullable<String> name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductModel productModel = (ProductModel) o;
    return Objects.equals(this.version, productModel.version) &&
        Objects.equals(this.id, productModel.id) &&
        Objects.equals(this.cost, productModel.cost) &&
        equalsNullable(this.name, productModel.name);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(version, id, cost, hashCodeNullable(name));
  }

  private static <T> int hashCodeNullable(JsonNullable<T> a) {
    if (a == null) {
      return 1;
    }
    return a.isPresent() ? Arrays.deepHashCode(new Object[]{a.get()}) : 31;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductModel {\n");
    sb.append("    version: ").append(toIndentedString(version)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    cost: ").append(toIndentedString(cost)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

