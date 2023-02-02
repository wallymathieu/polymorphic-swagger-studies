package se.gewalli.polymorphic_swagger.entities;

import java.time.Instant;
import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Order(
        @JsonProperty("id") int id,
        @JsonProperty("customer") Customer customer,
        @JsonProperty("orderDate") Instant orderDate,
        @JsonProperty("products") Collection<Product> products,
        @JsonProperty("version") int version) {
}
