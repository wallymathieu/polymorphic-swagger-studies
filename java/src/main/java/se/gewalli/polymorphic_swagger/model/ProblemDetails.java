package se.gewalli.polymorphic_swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Arrays;
import java.util.HashMap;
import org.openapitools.jackson.nullable.JsonNullable;
import io.swagger.v3.oas.annotations.media.Schema;


/**
 * ProblemDetails
 */

public class ProblemDetails extends HashMap<String, Object> {

  @JsonProperty("type")
  private JsonNullable<String> type = JsonNullable.undefined();

  @JsonProperty("title")
  private JsonNullable<String> title = JsonNullable.undefined();

  @JsonProperty("status")
  private JsonNullable<Integer> status = JsonNullable.undefined();

  @JsonProperty("detail")
  private JsonNullable<String> detail = JsonNullable.undefined();

  @JsonProperty("instance")
  private JsonNullable<String> instance = JsonNullable.undefined();

  public ProblemDetails type(String type) {
    this.type = JsonNullable.of(type);
    return this;
  }

  /**
   * Get type
   * @return type
  */
  
  @Schema(name = "type", required = false)
  public JsonNullable<String> getType() {
    return type;
  }

  public void setType(JsonNullable<String> type) {
    this.type = type;
  }

  public ProblemDetails title(String title) {
    this.title = JsonNullable.of(title);
    return this;
  }

  /**
   * Get title
   * @return title
  */
  
  @Schema(name = "title", required = false)
  public JsonNullable<String> getTitle() {
    return title;
  }

  public void setTitle(JsonNullable<String> title) {
    this.title = title;
  }

  public ProblemDetails status(Integer status) {
    this.status = JsonNullable.of(status);
    return this;
  }

  /**
   * Get status
   * @return status
  */
  
  @Schema(name = "status", required = false)
  public JsonNullable<Integer> getStatus() {
    return status;
  }

  public void setStatus(JsonNullable<Integer> status) {
    this.status = status;
  }

  public ProblemDetails detail(String detail) {
    this.detail = JsonNullable.of(detail);
    return this;
  }

  /**
   * Get detail
   * @return detail
  */
  
  @Schema(name = "detail", required = false)
  public JsonNullable<String> getDetail() {
    return detail;
  }

  public void setDetail(JsonNullable<String> detail) {
    this.detail = detail;
  }

  public ProblemDetails instance(String instance) {
    this.instance = JsonNullable.of(instance);
    return this;
  }

  /**
   * Get instance
   * @return instance
  */
  
  @Schema(name = "instance", required = false)
  public JsonNullable<String> getInstance() {
    return instance;
  }

  public void setInstance(JsonNullable<String> instance) {
    this.instance = instance;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProblemDetails problemDetails = (ProblemDetails) o;
    return equalsNullable(this.type, problemDetails.type) &&
        equalsNullable(this.title, problemDetails.title) &&
        equalsNullable(this.status, problemDetails.status) &&
        equalsNullable(this.detail, problemDetails.detail) &&
        equalsNullable(this.instance, problemDetails.instance) &&
        super.equals(o);
  }

  private static <T> boolean equalsNullable(JsonNullable<T> a, JsonNullable<T> b) {
    return a == b || (a != null && b != null && a.isPresent() && b.isPresent() && Objects.deepEquals(a.get(), b.get()));
  }

  @Override
  public int hashCode() {
    return Objects.hash(hashCodeNullable(type), hashCodeNullable(title), hashCodeNullable(status), hashCodeNullable(detail), hashCodeNullable(instance), super.hashCode());
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
    sb.append("class ProblemDetails {\n");
    sb.append("    ").append(toIndentedString(super.toString())).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    status: ").append(toIndentedString(status)).append("\n");
    sb.append("    detail: ").append(toIndentedString(detail)).append("\n");
    sb.append("    instance: ").append(toIndentedString(instance)).append("\n");
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

