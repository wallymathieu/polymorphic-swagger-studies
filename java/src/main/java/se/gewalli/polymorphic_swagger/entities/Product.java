package se.gewalli.polymorphic_swagger.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(
        @JsonProperty("id") int id,
        @JsonProperty("cost") float cost,
        @JsonProperty("name") String name,
        @JsonProperty("version") int version) {
}
