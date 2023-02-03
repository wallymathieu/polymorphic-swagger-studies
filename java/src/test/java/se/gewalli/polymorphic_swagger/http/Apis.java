package se.gewalli.polymorphic_swagger.http;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import se.gewalli.polymorphic_swagger.model.AddOrder;
import se.gewalli.polymorphic_swagger.model.AddProduct;
import se.gewalli.polymorphic_swagger.model.AddProductToOrder;
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProductModel;

import java.math.BigInteger;
import java.util.List;

interface Products {
    @GET("/api/v1/products")
    Call<List<ProductModel>> list();

    @GET("/api/v1/products/{id}")
    Call<ProductModel> get(@Path("id") BigInteger id);

    @POST("/api/v1/products")
    Call<ProductModel> post(@Body AddProduct customer);
}

interface Customers {
    @GET("/api/v1/customers")
    Call<List<CustomerModel>> list();

    @GET("/api/v1/customers/{id}")
    Call<CustomerModel> get(@Path("id") BigInteger id);

    @POST("/api/v1/customers")
    Call<CustomerModel> post(@Body CreateCustomer customer);
}

interface Orders {
    @GET("/api/orders")
    Call<List<OrderModel>> list();

    @GET("/api/orders/{id}")
    Call<OrderModel> get(@Path("id") BigInteger id);

    @POST("/api/orders")
    Call<OrderModel> post(@Body AddOrder createOrder);

    @POST("/api/orders/{id}/products")
    Call<OrderModel> addProduct(@Path("id") BigInteger id, @Body AddProductToOrder createOrder);
}