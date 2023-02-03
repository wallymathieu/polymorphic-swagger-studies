package se.gewalli.polymorphic_swagger.model;

import java.math.BigInteger;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AddProductToOrder
 */

public class AddProductToOrder {

  @JsonProperty("productId")
  private BigInteger productId;

  public AddProductToOrder productId(BigInteger productId) {
    this.productId = productId;
    return this;
  }

  /**
   * Get productId
   * @return productId
  */
  @NotNull 
  @Schema(name = "productId", required = true)
  public BigInteger getProductId() {
    return productId;
  }

  public void setProductId(BigInteger productId) {
    this.productId = productId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddProductToOrder addProductToOrder = (AddProductToOrder) o;
    return Objects.equals(this.productId, addProductToOrder.productId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productId);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddProductToOrder {\n");
    sb.append("    productId: ").append(toIndentedString(productId)).append("\n");
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

