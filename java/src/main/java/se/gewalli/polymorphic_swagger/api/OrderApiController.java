package se.gewalli.polymorphic_swagger.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.polymorpicSwagger.base-path:}")
public class OrderApiController implements OrderApi {

        @Autowired
        private Repository repository;
        @Autowired
        private CommandsHandler commandsHandler;

        @Autowired
        public OrderApiController() {
        }


        @Override
        public CompletableFuture<ResponseEntity<OrderModel>> addOrder(@Valid AddOrder body) {
                Command command = new AddOrderCommand(UUID.randomUUID(), 0,
                                UUIDUtils.convertFromBigInteger(body.getCustomer()), Instant.now());

                return commandsHandler.handle(command)
                                .thenApply(result -> result.fold(
                                                len -> new ResponseEntity<>(
                                                        Mappers.mapToOrderModel(repository
                                                                                .tryGetOrder(command.id()).get()),
                                                                HttpStatus.OK),
                                                err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
        }

        @Override
        public CompletableFuture<ResponseEntity<OrderModel>> addProductToOrder(BigInteger id,
                        @Valid AddProductToOrder addProductToOrder) {
                UUID orderId = UUIDUtils.convertFromBigInteger(id);
                Command command = new AddProductToOrderCommand(UUID.randomUUID(), 0, orderId,
                                UUIDUtils.convertFromBigInteger(addProductToOrder.getProductId()));
                return commandsHandler.handle(command)
                                .thenApply(result -> result.fold(
                                                len -> new ResponseEntity<>(
                                                        Mappers.mapToOrderModel(
                                                                                repository.tryGetOrder(orderId).get()),
                                                                HttpStatus.OK),
                                                err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
        }

        @Override
        public CompletableFuture<ResponseEntity<OrderModel>> getOrder(BigInteger id) {
                return CompletableFuture
                                .supplyAsync(() -> repository.tryGetOrder(UUIDUtils.convertFromBigInteger(id))
                                                .map(Mappers::mapToOrderModel).map(ResponseEntity::ok)
                                                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
        }

        @Override
        public CompletableFuture<ResponseEntity<List<OrderModel>>> getOrders() {
                return CompletableFuture.supplyAsync(
                                () -> ResponseEntity.ok(
                                                repository.getOrders().stream().map(Mappers::mapToOrderModel).toList()));
        }

}
