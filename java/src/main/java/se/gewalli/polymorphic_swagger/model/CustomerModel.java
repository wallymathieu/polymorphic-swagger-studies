package se.gewalli.polymorphic_swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.Arrays;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * CustomerModel
 */

public class CustomerModel {

  @JsonProperty("id")
  private BigInteger id;

  @JsonProperty("lastname")
  private JsonNullable<String> lastname = JsonNullable.undefined();

  @JsonProperty("firstname")
  private JsonNullable<String> firstname = JsonNullable.undefined();

  public CustomerModel id(BigInteger id) {
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

  public CustomerModel lastname(String lastname) {
    this.lastname = JsonNullable.of(lastname);
    return this;
  }

  /**
   * Get lastname
   * @return lastname
  */
  
  @Schema(name = "lastname", required = false)
  public JsonNullable<String> getLastname() {
    return lastname;
  }

  public void setLastname(JsonNullable<String> lastname) {
    this.lastname = lastname;
  }

  public CustomerModel firstname(String firstname) {
    this.firstname = JsonNullable.of(firstname);
    return this;
  }

  /**
   * Get firstname
   * @return firstname
  */
  
  @Schema(name = "firstname", required = false)
  public JsonNullable<String> getFirstname() {
    return firstname;
  }

  public void setFirstname(JsonNullable<String> firstname) {
    this.firstname = firstname;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CustomerModel customerModel = (CustomerModel) o;
    return Objects.equals(this.id, customerModel.id) &&
        equalsNullable(this.lastname, customerModel.lastname) &&
        equalsNullable(this.firstname, customerModel.firstname);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, hashCodeNullable(lastname), hashCodeNullable(firstname));
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
    sb.append("class CustomerModel {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
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

