package se.gewalli.polymorphic_swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import se.gewalli.polymorphic_swagger.CommandsHandler;
import se.gewalli.polymorphic_swagger.commands.Command;
import se.gewalli.polymorphic_swagger.data.Repository;
import se.gewalli.polymorphic_swagger.data.UUIDUtils;
import se.gewalli.polymorphic_swagger.entities.Customer;
import se.gewalli.polymorphic_swagger.entities.Order;
import se.gewalli.polymorphic_swagger.entities.Product;
import se.gewalli.polymorphic_swagger.model.AddOrder;
import se.gewalli.polymorphic_swagger.model.AddProduct;
import se.gewalli.polymorphic_swagger.model.AddProductToOrder;
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProductModel;
import se.gewalli.polymorphic_swagger.commands.AddProductToOrderCommand;
import se.gewalli.polymorphic_swagger.commands.AddCustomerCommand;
import se.gewalli.polymorphic_swagger.commands.AddProductCommand;
import se.gewalli.polymorphic_swagger.commands.AddOrderCommand;

import java.math.BigInteger;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.polymorpicSwagger.base-path:}")
public class ApiController implements Api {

    private final NativeWebRequest request;
    @Autowired
    private Repository repository;
    @Autowired
    private CommandsHandler commandsHandler;

    @Autowired
    public ApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public CompletableFuture<ResponseEntity<OrderModel>> addOrder(@Valid AddOrder body) {
        Command command = new AddOrderCommand(UUID.randomUUID(), 0, UUIDUtils.convertFromBigInteger(body.getCustomer()), Instant.now());
        return commandsHandler.handle(command)
                .thenApply(result -> result.fold(
                        len -> new ResponseEntity<>(this.mapToOrderModel(repository.tryGetOrder(command.id()).get()),
                                HttpStatus.OK),
                        err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @Override
    public CompletableFuture<ResponseEntity<ProductModel>> addProduct(@Valid AddProduct body) {
        Command command = new AddProductCommand(UUID.randomUUID(), 0, body.getCost().intValue(), body.getName());
        return commandsHandler.handle(command)
                .thenApply(result -> result.fold(
                        len -> new ResponseEntity<>(this.mapToProductModel(repository.tryGetProduct(command.id()).get()),
                                HttpStatus.OK),
                        err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> addProductToOrder(BigInteger id,
            @Valid AddProductToOrder addProductToOrder) {
        Command command = new AddProductToOrderCommand(UUID.randomUUID(), 0, UUIDUtils.convertFromBigInteger(id), UUIDUtils.convertFromBigInteger(addProductToOrder.getProductId()));
        return commandsHandler.handle(command).thenApply(result -> result.fold(a -> new ResponseEntity<>(HttpStatus.OK),
                err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @Override
    public CompletableFuture<ResponseEntity<CustomerModel>> createCustomer(@Valid CreateCustomer createCustomer) {
        Command command = new AddCustomerCommand(UUID.randomUUID(),0, createCustomer.getFirstname(), createCustomer.getLastname());
        return commandsHandler.handle(command)
                .thenApply(result -> result.fold(
                        len -> new ResponseEntity<>(this.mapToCustomerModel(repository.tryGetCustomer(command.id()).get()),
                                HttpStatus.OK),
                        err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @Override
    public CompletableFuture<ResponseEntity<CustomerModel>> getCustomer(BigInteger id) {
        return CompletableFuture
                .supplyAsync(() -> repository.tryGetCustomer(UUIDUtils.convertFromBigInteger(id)).map(this::mapToCustomerModel).map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<CustomerModel>>> getCustomers() {
        return CompletableFuture.supplyAsync(
                () -> ResponseEntity.ok(repository.getCustomers().stream().map(this::mapToCustomerModel).toList()));
    }

    @Override
    public CompletableFuture<ResponseEntity<OrderModel>> getOrder(BigInteger id) {
        return CompletableFuture
                .supplyAsync(() -> repository.tryGetOrder(UUIDUtils.convertFromBigInteger(id)).map(this::mapToOrderModel).map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<OrderModel>>> getOrders() {
        return CompletableFuture.supplyAsync(
                () -> ResponseEntity.ok(repository.getOrders().stream().map(this::mapToOrderModel).toList()));
    }

    @Override
    public CompletableFuture<ResponseEntity<ProductModel>> getProduct(BigInteger id) {
        return CompletableFuture
                .supplyAsync(() -> repository.tryGetProduct(UUIDUtils.convertFromBigInteger(id)).map(this::mapToProductModel).map(ResponseEntity::ok)
                        .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<ProductModel>>> getProducts() {
        return CompletableFuture.supplyAsync(
                () -> ResponseEntity.ok(repository.getProducts().stream().map(this::mapToProductModel).toList()));
    }

    private OrderModel mapToOrderModel(Order order) {
        return new OrderModel()
                .id(UUIDUtils.convertToBigInteger(order.id()))
                .customer(mapToCustomerModel(order.customer()))
                .orderDate(OffsetDateTime.ofInstant(order.orderDate(), ZoneOffset.UTC))
                .products(order.products().stream().map(this::mapToProductModel).toList());
    }

    private ProductModel mapToProductModel(Product product) {
        return new ProductModel()
                .cost(product.cost())
                .id(UUIDUtils.convertToBigInteger(product.id()))
                .name(product.name())
                .version(String.valueOf(product.version()));
    }

    private CustomerModel mapToCustomerModel(Customer customer) {
        return new CustomerModel()
                .firstname(customer.firstName())
                .lastname(customer.lastName())
                .id(UUIDUtils.convertToBigInteger(customer.id()));
    }
}
