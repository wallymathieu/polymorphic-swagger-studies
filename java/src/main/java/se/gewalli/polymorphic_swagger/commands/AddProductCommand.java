package se.gewalli.polymorphic_swagger.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.entities.Product;

public record AddProductCommand(@JsonProperty("id") int id,
                                @JsonProperty("version") int version,
                                @JsonProperty("cost") float cost,
                                @JsonProperty("name") String name) implements Command {

    @Override
    public CommandType getType() {
        return CommandType.AddProductCommand;
    }

    @Override
    public void run(Repository repository) {
        repository.save(new Product(id, cost, name, version));
    }
}
