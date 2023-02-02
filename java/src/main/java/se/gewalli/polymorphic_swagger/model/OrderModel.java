package se.gewalli.polymorphic_swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openapitools.jackson.nullable.JsonNullable;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.Valid;
import io.swagger.v3.oas.annotations.media.Schema;


import javax.annotation.Generated;

/**
 * OrderModel
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-28T19:45:56.796705+02:00[Europe/Athens]")
public class OrderModel {

  @JsonProperty("id")
  private Integer id;

  @JsonProperty("customer")
  private CustomerModel customer;

  @JsonProperty("orderDate")
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
  private OffsetDateTime orderDate;

  @JsonProperty("products")
  @Valid
  private JsonNullable<List<ProductModel>> products = JsonNullable.undefined();

  public OrderModel id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  
  @Schema(name = "id", required = false)
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public OrderModel customer(CustomerModel customer) {
    this.customer = customer;
    return this;
  }

  /**
   * Get customer
   * @return customer
  */
  @Valid 
  @Schema(name = "customer", required = false)
  public CustomerModel getCustomer() {
    return customer;
  }

  public void setCustomer(CustomerModel customer) {
    this.customer = customer;
  }

  public OrderModel orderDate(OffsetDateTime orderDate) {
    this.orderDate = orderDate;
    return this;
  }

  /**
   * Get orderDate
   * @return orderDate
  */
  @Valid 
  @Schema(name = "orderDate", required = false)
  public OffsetDateTime getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(OffsetDateTime orderDate) {
    this.orderDate = orderDate;
  }

  public OrderModel products(List<ProductModel> products) {
    this.products = JsonNullable.of(products);
    return this;
  }

  public OrderModel addProductsItem(ProductModel productsItem) {
    if (this.products == null || !this.products.isPresent()) {
      this.products = JsonNullable.of(new ArrayList<>());
    }
    this.products.get().add(productsItem);
    return this;
  }

  /**
   * Get products
   * @return products
  */
  @Valid 
  @Schema(name = "products", required = false)
  public JsonNullable<List<ProductModel>> getProducts() {
    return products;
  }

  public void setProducts(JsonNullable<List<ProductModel>> products) {
    this.products = products;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OrderModel orderModel = (OrderModel) o;
    return Objects.equals(this.id, orderModel.id) &&
        Objects.equals(this.customer, orderModel.customer) &&
        Objects.equals(this.orderDate, orderModel.orderDate) &&
        equalsNullable(this.products, orderModel.products);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, customer, orderDate, hashCodeNullable(products));
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
    sb.append("class OrderModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    customer: ").append(toIndentedString(customer)).append("\n");
    sb.append("    orderDate: ").append(toIndentedString(orderDate)).append("\n");
    sb.append("    products: ").append(toIndentedString(products)).append("\n");
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

