package se.gewalli.polymorphic_swagger.commands;

import java.util.Map;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.entities.Product;

public record AddProductCommand(@JsonProperty("id") UUID id,
                                @JsonProperty("version") int version,
                                @JsonProperty("cost") float cost,
                                @JsonProperty("name") String name,
                                @JsonProperty("properties") Map<String,String> properties) implements Command {

    @Override
    public CommandType getType() {
        return CommandType.AddProductCommand;
    }

    @Override
    public void run(Repository repository) {
        repository.save(new Product(id, cost, name, version, properties));
    }
}
