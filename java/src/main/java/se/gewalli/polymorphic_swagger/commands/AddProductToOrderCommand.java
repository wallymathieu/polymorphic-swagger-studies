package se.gewalli.polymorphic_swagger.commands;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

import se.gewalli.polymorphic_swagger.data.EntityNotFound;
import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.entities.Order;
import se.gewalli.polymorphic_swagger.entities.Product;
public record AddProductToOrderCommand(@JsonProperty("id") int id,
                                       @JsonProperty("version") int version,
                                       @JsonProperty("orderId") int orderId,
                                       @JsonProperty("productId") int productId) implements Command {
    @Override
    public CommandType getType() {
        return CommandType.AddProductToOrderCommand;
    }

    @Override
    public void run(Repository repository) throws EntityNotFound {
        var order = repository.getOrder(orderId);
        var productList = new ArrayList<Product>(order.products());
        productList.add(repository.getProduct(productId));
        repository.save(new Order(order.id(), order.customer(), order.orderDate(), productList, order.version() + 1));
    }
}