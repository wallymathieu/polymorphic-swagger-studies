package se.gewalli.polymorphic_swagger.data;

import java.util.Collection;
import java.util.Optional;

import se.gewalli.polymorphic_swagger.entities.Customer;
import se.gewalli.polymorphic_swagger.entities.Order;
import se.gewalli.polymorphic_swagger.entities.Product;

public abstract class Repository {
    public abstract Optional<Customer> tryGetCustomer(int customerId);

    public abstract Optional<Product> tryGetProduct(int productId);

    public abstract Optional<Order> tryGetOrder(int orderId);

    public abstract void save(Product product);

    public abstract void save(Order order);

    public abstract void save(Customer customer);

    public Customer getCustomer(int customerId) throws EntityNotFound {
        return tryGetCustomer(customerId)
                .orElseThrow(() -> new EntityNotFound(String.format("Could not find customer %d", customerId)));
    }

    public Product getProduct(int productId) throws EntityNotFound {
        return tryGetProduct(productId)
                .orElseThrow(() -> new EntityNotFound(String.format("Could not find product %d", productId)));
    }

    public Order getOrder(int orderId) throws EntityNotFound {
        return tryGetOrder(orderId)
                .orElseThrow(() -> new EntityNotFound(String.format("Could not find order %d", orderId)));
    }

    public abstract Collection<Customer> getCustomers();

    public abstract Collection<Product> getProducts();

    public abstract Collection<Order> getOrders();
}