package se.gewalli.polymorphic_swagger.api;

import se.gewalli.polymorphic_swagger.model.AddProduct;
import java.util.Map;
import se.gewalli.polymorphic_swagger.model.ProblemDetails;
import se.gewalli.polymorphic_swagger.model.ProductModel;
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
@Tag(name = "product-api", description = "the api API")
public interface ProductApi {


    /**
     * GET /api/v1/products :
     *
     * @return Success (status code 200)
     */
    @Operation(operationId = "apiV1ProductsGet", summary = "", tags = { "Products" }, responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = ProductModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = ProductModel.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/products", produces = { "text/plain",
            "application/json", "text/json" })
    CompletableFuture<ResponseEntity<List<ProductModel>>> getProducts(

    );

    /**
     * GET /api/v1/products/{id} :
     *
     * @param id (required)
     * @return Not Found (status code 404)
     *         or Success (status code 200)
     */
    @Operation(operationId = "apiV1ProductsIdGet", summary = "", tags = { "Products" }, responses = {
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = ProblemDetails.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = ProblemDetails.class))
            }),
            @ApiResponse(responseCode = "200", description = "Success", content = {
                    @Content(mediaType = "text/plain", schema = @Schema(implementation = ProductModel.class)),
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)),
                    @Content(mediaType = "text/json", schema = @Schema(implementation = ProductModel.class))
            })
    })
    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/products/{id}", produces = { "text/plain",
            "application/json", "text/json" })
    CompletableFuture<ResponseEntity<ProductModel>> getProduct(
            @Parameter(name = "id", description = "", required = true) @PathVariable("id") BigInteger id);

    /**
     * POST /api/v1/products : Add product to available products
     * You could for instance add products using &#x60;&#x60;&#x60;JSON {
     * \&quot;cost\&quot;:124, \&quot;name\&quot;: \&quot;tea\&quot; }
     * &#x60;&#x60;&#x60;
     *
     * @param apiV1ProductsGetRequest (optional)
     * @return The product was added successfully (status code 200)
     *         or (status code 400)
     */
    @Operation(operationId = "apiV1ProductsPost", summary = "Add product to available products", tags = {
            "Products" }, responses = {
                    @ApiResponse(responseCode = "200", description = "The product was added successfully"),
                    @ApiResponse(responseCode = "400", description = "", content = {
                            @Content(mediaType = "text/plain", schema = @Schema(implementation = Map.class)),
                            @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)),
                            @Content(mediaType = "text/json", schema = @Schema(implementation = Map.class))
                    })
            })
    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/products", produces = { "text/plain",
            "application/json", "text/json" }, consumes = { "application/json", "text/json", "application/*+json" })
    CompletableFuture<ResponseEntity<ProductModel>> addProduct(
            @Parameter(name = "ApiV1ProductsGetRequest", description = "") @Valid @RequestBody(required = false) AddProduct apiV1ProductsGetRequest);

}
