package se.gewalli.polymorphic_swagger.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import se.gewalli.polymorphic_swagger.entities.Customer;
import se.gewalli.polymorphic_swagger.entities.Order;
import se.gewalli.polymorphic_swagger.entities.Product;

public class InMemoryRepository extends Repository {
    Map<Integer, Customer> customerMap = new HashMap<>();
    Map<Integer, Product> productMap = new HashMap<>();
    Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public Optional<Customer> tryGetCustomer(int customerId) {
        return Optional.ofNullable(customerMap.get(customerId));
    }

    @Override
    public Optional<Product> tryGetProduct(int productId) {
        return Optional.ofNullable(productMap.get(productId));
    }

    @Override
    public Optional<Order> tryGetOrder(int orderId) {
        return Optional.ofNullable(orderMap.get(orderId));
    }

    @Override
    public void save(Product product) {
        productMap.put(product.id(), product);
    }

    @Override
    public void save(Order order) {
        orderMap.put(order.id(), order);
    }

    @Override
    public void save(Customer customer) {
        customerMap.put(customer.id(), customer);
    }

    @Override
    public Collection<Customer> getCustomers() {
        return customerMap.values();
    }

    @Override
    public Collection<Product> getProducts() {
        return productMap.values();
    }

    @Override
    public Collection<Order> getOrders() {
        return orderMap.values();
    }

}
