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
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.commands.AddCustomerCommand;
import java.math.BigInteger;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

@Controller
@RequestMapping("${openapi.polymorpicSwagger.base-path:}")
public class CustomerApiController implements CustomerApi {

        @Autowired
        private Repository repository;
        @Autowired
        private CommandsHandler commandsHandler;

        @Autowired
        public CustomerApiController() {
        }


        @Override
        public CompletableFuture<ResponseEntity<CustomerModel>> createCustomer(@Valid CreateCustomer createCustomer) {
                Command command = new AddCustomerCommand(UUID.randomUUID(), 0, createCustomer.getFirstname(),
                                createCustomer.getLastname());
                return commandsHandler.handle(command)
                                .thenApply(result -> result.fold(
                                                len -> new ResponseEntity<>(
                                                        Mappers.mapToCustomerModel(repository
                                                                                .tryGetCustomer(command.id()).get()),
                                                                HttpStatus.OK),
                                                err -> new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR)));
        }

        @Override
        public CompletableFuture<ResponseEntity<CustomerModel>> getCustomer(BigInteger id) {
                return CompletableFuture
                                .supplyAsync(() -> repository.tryGetCustomer(UUIDUtils.convertFromBigInteger(id))
                                                .map(Mappers::mapToCustomerModel).map(ResponseEntity::ok)
                                                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null)));
        }

        @Override
        public CompletableFuture<ResponseEntity<List<CustomerModel>>> getCustomers() {
                return CompletableFuture.supplyAsync(
                                () -> ResponseEntity.ok(repository.getCustomers().stream().map(Mappers::mapToCustomerModel)
                                                .toList()));
        }

}
