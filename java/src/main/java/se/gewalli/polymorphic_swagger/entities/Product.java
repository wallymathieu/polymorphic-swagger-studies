package se.gewalli.polymorphic_swagger.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(
                @JsonProperty("id") UUID id,
                @JsonProperty("cost") float cost,
                @JsonProperty("name") String name,
                @JsonProperty("version") int version) {

}
