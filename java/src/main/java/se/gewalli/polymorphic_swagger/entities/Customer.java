package se.gewalli.polymorphic_swagger.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Customer(
        @JsonProperty("id") int id,
        @JsonProperty("firstname") String firstName,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("version") int version) {
}
