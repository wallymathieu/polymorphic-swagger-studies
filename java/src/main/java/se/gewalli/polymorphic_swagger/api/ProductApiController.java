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
import se.gewalli.polymorphic_swagger.model.AddProduct;
import se.gewalli.polymorphic_swagger.model.AddProduct2;
import se.gewalli.polymorphic_swagger.model.ProductModel;
import se.gewalli.polymorphic_swagger.commands.AddProductCommand;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.polymorpicSwagger.base-path:}")
public class ProductApiController implements ProductApi {

        @Autowired
        private Repository repository;
        @Autowired
        private CommandsHandler commandsHandler;

        @Autowired
        public ProductApiController() {
        }

        @Override
        public CompletableFuture<ResponseEntity<ProductModel>> addProduct(@Valid AddProduct body) {
                Map<String,String> properties = body instanceof AddProduct2? ((AddProduct2)body ).getProperties():Map.of();
                Command command = new AddProductCommand(UUID.randomUUID(), 0, body.getCost().intValue(),
                                body.getName(), properties);
                return commandsHandler.handle(command)
                                .thenApply(result -> result.fold(
                                                len -> new ResponseEntity<>(
                                                                Mappers.mapToProductModel(repository
                                                                                .tryGetProduct(command.id()).get()),
                                                                HttpStatus.OK),
                                                err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
        }

        @Override
        public CompletableFuture<ResponseEntity<ProductModel>> getProduct(BigInteger id) {
                return CompletableFuture
                                .supplyAsync(() -> repository.tryGetProduct(UUIDUtils.convertFromBigInteger(id))
                                                .map(Mappers::mapToProductModel).map(ResponseEntity::ok)
                                                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
        }

        @Override
        public CompletableFuture<ResponseEntity<List<ProductModel>>> getProducts() {
                return CompletableFuture.supplyAsync(
                                () -> ResponseEntity
                                                .ok(repository.getProducts().stream().map(Mappers::mapToProductModel)
                                                                .toList()));
        }

}
