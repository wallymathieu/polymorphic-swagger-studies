package se.gewalli.polymorphic_swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * CreateCustomer
 */

public class CreateCustomer {

  @JsonProperty("lastname")
  private String lastname;

  @JsonProperty("firstname")
  private String firstname;
  public CreateCustomer(){}

  public CreateCustomer(String firstname, String lastname) {
    this.firstname = firstname;
    this.lastname = lastname;
  }

  public CreateCustomer lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  /**
   * 
   * @return lastname
  */
  @NotNull @Size(min = 3, max = 60) 
  @Schema(name = "lastname", description = "", required = true)
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public CreateCustomer firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  /**
   * 
   * @return firstname
  */
  @NotNull @Size(min = 3, max = 60) 
  @Schema(name = "firstname", description = "", required = true)
  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
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
    CreateCustomer createCustomer = (CreateCustomer) o;
    return Objects.equals(this.lastname, createCustomer.lastname) &&
        Objects.equals(this.firstname, createCustomer.firstname);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lastname, firstname);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CreateCustomer {\n");
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

