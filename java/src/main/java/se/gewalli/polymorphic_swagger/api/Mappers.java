package se.gewalli.polymorphic_swagger.api;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import se.gewalli.polymorphic_swagger.data.UUIDUtils;
import se.gewalli.polymorphic_swagger.entities.Customer;
import se.gewalli.polymorphic_swagger.entities.Order;
import se.gewalli.polymorphic_swagger.entities.Product;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProductModel;

public class Mappers {
    public static OrderModel mapToOrderModel(Order order) {
        return new OrderModel()
                .id(UUIDUtils.convertToBigInteger(order.id()))
                .customer(mapToCustomerModel(order.customer()))
                .orderDate(OffsetDateTime.ofInstant(order.orderDate(), ZoneOffset.UTC))
                .products(order.products().stream().map(Mappers::mapToProductModel).toList());
    }

    public static ProductModel mapToProductModel(Product product) {
        return new ProductModel()
                .cost(product.cost())
                .id(UUIDUtils.convertToBigInteger(product.id()))
                .name(product.name())
                .version(String.valueOf(product.version()));
    }

    public static CustomerModel mapToCustomerModel(Customer customer) {
        return new CustomerModel()
                .firstname(customer.firstName())
                .lastname(customer.lastName())
                .id(UUIDUtils.convertToBigInteger(customer.id()));
    }
}
