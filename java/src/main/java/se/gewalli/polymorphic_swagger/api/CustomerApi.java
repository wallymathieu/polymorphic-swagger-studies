package se.gewalli.polymorphic_swagger.api;

import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import java.util.Map;
import se.gewalli.polymorphic_swagger.model.ProblemDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@Validated
@Tag(name = "customer-api", description = "the Customer API")
public interface CustomerApi {

    /**
     * GET /api/v1/customers :
     *
     * @return Success (status code 200)
     */
    @Operation(operationId = "apiV1CustomersGet", summary = "", tags = { "Customers" }, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = CustomerModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerModel.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = CustomerModel.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/customers", produces = { "text/plain",
            "application/json", "text/json" })
    CompletableFuture<ResponseEntity<List<CustomerModel>>> getCustomers(

    );

    /**
     * GET /api/v1/customers/{id} :
     *
     * @param id (required)
     * @return Not Found (status code 404)
     *         or Success (status code 200)
     */
    @Operation(operationId = "apiV1CustomersIdGet", summary = "", tags = { "Customers" }, responses = {
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = ProblemDetails.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = ProblemDetails.class))
            }),
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = CustomerModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerModel.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = CustomerModel.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/customers/{id}", produces = { "text/plain",
            "application/json", "text/json" })
    CompletableFuture<ResponseEntity<CustomerModel>> getCustomer(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") BigInteger id);

    /**
     * POST /api/v1/customers :
     *
     * @param createCustomer (optional)
     * @return Bad Request (status code 400)
     */
    @Operation(operationId = "apiV1CustomersPost", summary = "", tags = { "Customers" }, responses = {
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = Map.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = Map.class))
            })
    })
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/customers", produces = { "text/plain",
            "application/json", "text/json" }, consumes = { "application/json", "text/json", "application/*+json" })
    CompletableFuture<ResponseEntity<CustomerModel>> createCustomer(
            @Parameter(name = "CreateCustomer", description = "") @Valid @RequestBody(required = false) CreateCustomer createCustomer);


}