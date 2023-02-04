package se.gewalli.polymorphic_swagger.entities;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Customer(
        @JsonProperty("id") UUID id,
        @JsonProperty("firstname") String firstName,
        @JsonProperty("lastname") String lastName,
        @JsonProperty("version") int version) {
}
