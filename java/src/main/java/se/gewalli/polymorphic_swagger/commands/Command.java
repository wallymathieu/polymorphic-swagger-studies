package se.gewalli.polymorphic_swagger.commands;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import se.gewalli.polymorphic_swagger.data.EntityNotFound;
import se.gewalli.polymorphic_swagger.data.Repository;

@JsonTypeInfo(include = As.PROPERTY, use = Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AddCustomerCommand.class),
        @JsonSubTypes.Type(value = AddOrderCommand.class),
        @JsonSubTypes.Type(value = AddProductCommand.class),
        @JsonSubTypes.Type(value = AddProductToOrderCommand.class),
})
public interface Command {
    CommandType getType();

    void run(Repository repository) throws EntityNotFound;

    int id();
}