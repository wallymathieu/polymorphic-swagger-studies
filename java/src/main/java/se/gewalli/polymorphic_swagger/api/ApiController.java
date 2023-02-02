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
import se.gewalli.polymorphic_swagger.entities.Order;
import se.gewalli.polymorphic_swagger.model.AddOrder;
import se.gewalli.polymorphic_swagger.model.AddProduct;
import se.gewalli.polymorphic_swagger.model.AddProductToOrder;
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProductModel;
import  se.gewalli.polymorphic_swagger.commands.AddProductToOrderCommand;
import java.util.List;
import java.util.Optional;
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
    public CompletableFuture<ResponseEntity<Void>> addOrder(@Valid AddOrder addOrder) {
        // TODO Auto-generated method stub
        return Api.super.addOrder(addOrder);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> addProduct(@Valid AddProduct apiV1ProductsGetRequest) {
        // TODO Auto-generated method stub
        return Api.super.addProduct(apiV1ProductsGetRequest);
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> addProductToOrder(Integer id,
            @Valid AddProductToOrder addProductToOrder) {
      Command command = new AddProductToOrderCommand(0, 0, id, addProductToOrder.getProductId());
        return commandsHandler.handle(command).thenApply(result ->
                result.fold(a -> new ResponseEntity<>(HttpStatus.OK),
                        err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
    }

    @Override
    public CompletableFuture<ResponseEntity<Void>> createCustomer(@Valid CreateCustomer createCustomer) {
        // TODO Auto-generated method stub
        return Api.super.createCustomer(createCustomer);
    }

    @Override
    public CompletableFuture<ResponseEntity<CustomerModel>> getCustomer(Integer id) {
        // TODO Auto-generated method stub
        return Api.super.getCustomer(id);
    }

    @Override
    public CompletableFuture<ResponseEntity<List<CustomerModel>>> getCustomers() {
        // TODO Auto-generated method stub
        return Api.super.getCustomers();
    }
    private OrderModel mapToOrderModel(Order order){
        return new OrderModel();
    }

    @Override
    public CompletableFuture<ResponseEntity<OrderModel>> getOrder(Integer id) {
        return CompletableFuture.supplyAsync(()->repository.tryGetOrder(id).map( order-> this.mapToOrderModel(order) ).map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
    }

    @Override
    public CompletableFuture<ResponseEntity<List<OrderModel>>> getOrders() {
        // TODO Auto-generated method stub
        return Api.super.getOrders();
    }

    @Override
    public CompletableFuture<ResponseEntity<ProductModel>> getProduct(Integer id) {
        // TODO Auto-generated method stub
        return Api.super.getProduct(id);
    }

    @Override
    public CompletableFuture<ResponseEntity<List<ProductModel>>> getProducts() {
        // TODO Auto-generated method stub
        return Api.super.getProducts();
    }
}
