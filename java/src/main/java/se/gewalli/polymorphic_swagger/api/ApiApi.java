/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (6.2.1).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package se.gewalli.polymorphic_swagger.api;

import se.gewalli.polymorphic_swagger.model.AddOrder;
import se.gewalli.polymorphic_swagger.model.AddProduct;
import se.gewalli.polymorphic_swagger.model.AddProductToOrder;
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import java.util.Map;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProblemDetails;
import se.gewalli.polymorphic_swagger.model.ProductModel;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2023-01-28T19:45:56.796705+02:00[Europe/Athens]")
@Validated
@Tag(name = "api", description = "the api API")
public interface ApiApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * GET /api/v1/customers : 
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "apiV1CustomersGet",
        summary = "",
        tags = { "Customers" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = CustomerModel.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = CustomerModel.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = CustomerModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/customers",
        produces = { "text/plain", "application/json", "text/json" }
    )
    default CompletableFuture<ResponseEntity<List<CustomerModel>>> apiV1CustomersGet(
        
    ) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "{ \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/json"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/json";
                        ApiUtil.setExampleResponse(request, "text/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/plain"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/plain";
                        ApiUtil.setExampleResponse(request, "text/plain", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }, Runnable::run);

    }


    /**
     * GET /api/v1/customers/{id} : 
     *
     * @param id  (required)
     * @return Not Found (status code 404)
     *         or Success (status code 200)
     */
    @Operation(
        operationId = "apiV1CustomersIdGet",
        summary = "",
        tags = { "Customers" },
        responses = {
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
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/customers/{id}",
        produces = { "text/plain", "application/json", "text/json" }
    )
    default CompletableFuture<ResponseEntity<CustomerModel>> apiV1CustomersIdGet(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "{ \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/json"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/json";
                        ApiUtil.setExampleResponse(request, "text/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/plain"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/plain";
                        ApiUtil.setExampleResponse(request, "text/plain", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }, Runnable::run);

    }


    /**
     * POST /api/v1/customers : 
     *
     * @param createCustomer  (optional)
     * @return Bad Request (status code 400)
     */
    @Operation(
        operationId = "apiV1CustomersPost",
        summary = "",
        tags = { "Customers" },
        responses = {
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = Map.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/v1/customers",
        produces = { "text/plain", "application/json", "text/json" },
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    default CompletableFuture<ResponseEntity<Void>> apiV1CustomersPost(
        @Parameter(name = "CreateCustomer", description = "") @Valid @RequestBody(required = false) CreateCustomer createCustomer
    ) {
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));

    }


    /**
     * GET /api/v1/orders
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "apiV1OrdersGet",
        tags = { "Orders" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = OrderModel.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = OrderModel.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = OrderModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/orders",
        produces = { "text/plain", "application/json", "text/json" }
    )
    default CompletableFuture<ResponseEntity<List<OrderModel>>> apiV1OrdersGet(
        
    ) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "{ \"id\" : 0, \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"customer\" : { \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" }, \"products\" : [ null, null ] }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/json"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/json";
                        ApiUtil.setExampleResponse(request, "text/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/plain"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/plain";
                        ApiUtil.setExampleResponse(request, "text/plain", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }, Runnable::run);

    }


    /**
     * GET /api/v1/orders/{id}
     *
     * @param id  (required)
     * @return Not Found (status code 404)
     *         or Success (status code 200)
     */
    @Operation(
        operationId = "apiV1OrdersIdGet",
        tags = { "Orders" },
        responses = {
            @ApiResponse(responseCode = "404", description = "Not Found", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = ProblemDetails.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProblemDetails.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = ProblemDetails.class))
            }),
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = OrderModel.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = OrderModel.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = OrderModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/orders/{id}",
        produces = { "text/plain", "application/json", "text/json" }
    )
    default CompletableFuture<ResponseEntity<OrderModel>> apiV1OrdersIdGet(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "{ \"id\" : 0, \"orderDate\" : \"2000-01-23T04:56:07.000+00:00\", \"customer\" : { \"firstname\" : \"firstname\", \"id\" : 0, \"lastname\" : \"lastname\" }, \"products\" : [ null, null ] }";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/json"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/json";
                        ApiUtil.setExampleResponse(request, "text/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/plain"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/plain";
                        ApiUtil.setExampleResponse(request, "text/plain", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }, Runnable::run);

    }


    /**
     * POST /api/v1/orders/{id}/products : 
     * 
     *
     * @param id order id (required)
     * @param addProductToOrder  (optional)
     * @return Bad Request (status code 400)
     */
    @Operation(
        operationId = "apiV1OrdersIdProductsPost",
        summary = "",
        tags = { "Orders" },
        responses = {
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = Map.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/v1/orders/{id}/products",
        produces = { "text/plain", "application/json", "text/json" },
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    default CompletableFuture<ResponseEntity<Void>> apiV1OrdersIdProductsPost(
        @Parameter(name = "id", description = "order id", required = true) @PathVariable("id") Integer id,
        @Parameter(name = "AddProductToOrder", description = "") @Valid @RequestBody(required = false) AddProductToOrder addProductToOrder
    ) {
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));

    }


    /**
     * POST /api/v1/orders : 
     * 
     *
     * @param addOrder  (optional)
     * @return Bad Request (status code 400)
     */
    @Operation(
        operationId = "apiV1OrdersPost",
        summary = "",
        tags = { "Orders" },
        responses = {
            @ApiResponse(responseCode = "400", description = "Bad Request", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = Map.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/v1/orders",
        produces = { "text/plain", "application/json", "text/json" },
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    default CompletableFuture<ResponseEntity<Void>> apiV1OrdersPost(
        @Parameter(name = "AddOrder", description = "") @Valid @RequestBody(required = false) AddOrder addOrder
    ) {
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));

    }


    /**
     * GET /api/v1/products : 
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "apiV1ProductsGet",
        summary = "",
        tags = { "Products" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = ProductModel.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = ProductModel.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = ProductModel.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/products",
        produces = { "text/plain", "application/json", "text/json" }
    )
    default CompletableFuture<ResponseEntity<List<ProductModel>>> apiV1ProductsGet(
        
    ) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "null";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/json"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/json";
                        ApiUtil.setExampleResponse(request, "text/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/plain"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/plain";
                        ApiUtil.setExampleResponse(request, "text/plain", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }, Runnable::run);

    }


    /**
     * GET /api/v1/products/{id} : 
     *
     * @param id  (required)
     * @return Not Found (status code 404)
     *         or Success (status code 200)
     */
    @Operation(
        operationId = "apiV1ProductsIdGet",
        summary = "",
        tags = { "Products" },
        responses = {
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
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/api/v1/products/{id}",
        produces = { "text/plain", "application/json", "text/json" }
    )
    default CompletableFuture<ResponseEntity<ProductModel>> apiV1ProductsIdGet(
        @Parameter(name = "id", description = "", required = true) @PathVariable("id") Integer id
    ) {
        return CompletableFuture.supplyAsync(()-> {
            getRequest().ifPresent(request -> {
                for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                    if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                        String exampleString = "null";
                        ApiUtil.setExampleResponse(request, "application/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/json"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/json";
                        ApiUtil.setExampleResponse(request, "text/json", exampleString);
                        break;
                    }
                    if (mediaType.isCompatibleWith(MediaType.valueOf("text/plain"))) {
                        String exampleString = "Custom MIME type example not yet supported: text/plain";
                        ApiUtil.setExampleResponse(request, "text/plain", exampleString);
                        break;
                    }
                }
            });
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }, Runnable::run);

    }


    /**
     * POST /api/v1/products : Add product to available products
     * You could for instance add products using  &#x60;&#x60;&#x60;JSON  {    \&quot;cost\&quot;:124, \&quot;name\&quot;: \&quot;tea\&quot;  }  &#x60;&#x60;&#x60;
     *
     * @param apiV1ProductsGetRequest  (optional)
     * @return The product was added successfully (status code 200)
     *         or  (status code 400)
     */
    @Operation(
        operationId = "apiV1ProductsPost",
        summary = "Add product to available products",
        tags = { "Products" },
        responses = {
            @ApiResponse(responseCode = "200", description = "The product was added successfully"),
            @ApiResponse(responseCode = "400", description = "", content = {
                @Content(mediaType = "text/plain", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "application/json", schema = @Schema(implementation = Map.class)),
                @Content(mediaType = "text/json", schema = @Schema(implementation = Map.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/api/v1/products",
        produces = { "text/plain", "application/json", "text/json" },
        consumes = { "application/json", "text/json", "application/*+json" }
    )
    default CompletableFuture<ResponseEntity<Void>> apiV1ProductsPost(
        @Parameter(name = "ApiV1ProductsGetRequest", description = "") @Valid @RequestBody(required = false) AddProduct apiV1ProductsGetRequest
    ) {
        return CompletableFuture.completedFuture(new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED));

    }

}
