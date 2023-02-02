package se.gewalli.polymorphic_swagger.commands;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.entities.Customer;

public record AddCustomerCommand(@JsonProperty("id") int id,
                                 @JsonProperty("version") int version,
                                 @JsonProperty("firstname") String firstname,
                                 @JsonProperty("lastname") String lastname) implements Command {

    @Override
    public CommandType getType() {
        return CommandType.AddCustomerCommand;
    }

    @Override
    public void run(Repository repository) {
        repository.save(new Customer(id, firstname, lastname, version));
    }
}
