package se.gewalli.polymorphic_swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * AddOrder
 */

public class AddOrder {

  @JsonProperty("customer")
  private Integer customer;
  public AddOrder(){

  }
  public AddOrder(int customer) {
    this.customer=customer;
  }

public AddOrder customer(Integer customer) {
    this.customer = customer;
    return this;
  }

  /**
   * 
   * @return customer
  */
  @NotNull 
  @Schema(name = "customer", description = "", required = true)
  public Integer getCustomer() {
    return customer;
  }

  public void setCustomer(Integer customer) {
    this.customer = customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AddOrder addOrder = (AddOrder) o;
    return Objects.equals(this.customer, addOrder.customer);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customer);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AddOrder {\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
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

