package se.gewalli.polymorphic_swagger.entities;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Product(
                @JsonProperty("id") UUID id,
                @JsonProperty("cost") float cost,
                @JsonProperty("name") String name,
                @JsonProperty("version") int version,
                @JsonProperty("properties") Map<String,String> properties) {

    public boolean hasProperties() {
        return properties!=null && properties.size()>0;
    }

}
