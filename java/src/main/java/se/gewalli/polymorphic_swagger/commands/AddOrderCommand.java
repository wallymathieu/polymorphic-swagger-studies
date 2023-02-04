package se.gewalli.polymorphic_swagger.commands;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.gewalli.polymorphic_swagger.data.EntityNotFound;
import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.entities.Order;

public record AddOrderCommand(@JsonProperty("id") UUID id,
                              @JsonProperty("version") int version,
                              @JsonProperty("customer") UUID customer,
                              @JsonProperty("orderDate") Instant orderDate) implements Command {
    @Override
    public CommandType getType() {
        return CommandType.AddOrderCommand;
    }

    @Override
    public void run(Repository repository) throws EntityNotFound {
        repository.save(new Order(id,
                repository.getCustomer(customer),
                orderDate,
                new ArrayList<>(),
                version));
    }
}