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

import java.util.List;

interface Products {
    @GET("/api/products")
    Call<List<ProductModel>> list();

    @GET("/api/products/{id}")
    Call<ProductModel> get(@Path("id") int id);

    @POST("/api/products")
    Call<ProductModel> post(@Body AddProduct customer);
}

interface Customers {
    @GET("/api/customers")
    Call<List<CustomerModel>> list();

    @GET("/api/customers/{id}")
    Call<CustomerModel> get(@Path("id") int id);

    @POST("/api/customers")
    Call<CustomerModel> post(@Body CreateCustomer customer);
}

interface Orders {
    @GET("/api/orders")
    Call<List<OrderModel>> list();

    @GET("/api/orders/{id}")
    Call<OrderModel> get(@Path("id") int id);

    @POST("/api/orders")
    Call<OrderModel> post(@Body AddOrder createOrder);

    @POST("/api/orders/{id}/products")
    Call<OrderModel> addProduct(@Path("id") int id, @Body AddProductToOrder createOrder);
}