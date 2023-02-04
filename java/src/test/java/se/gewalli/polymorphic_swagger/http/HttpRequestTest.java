package se.gewalli.polymorphic_swagger.http;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import retrofit2.Response;
import se.gewalli.polymorphic_swagger.model.AddOrder;
import se.gewalli.polymorphic_swagger.model.AddProduct;
import se.gewalli.polymorphic_swagger.model.AddProduct2;
import se.gewalli.polymorphic_swagger.model.AddProductToOrder;
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProductModel;
import se.gewalli.polymorphic_swagger.model.ProductModelV2;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ApplicationUnderTest.class)
public class HttpRequestTest {
    @Autowired
    private Customers customers;
    @Autowired
    private Products products;
    @Autowired
    private Orders orders;

    @Test
    public void testCanCreateCustomersAndListThem() throws Exception {
        customers.post(new CreateCustomer("Firstname", "Lastname")).execute();
        customers.post(new CreateCustomer("Firstname", "Lastname")).execute();
        Response<List<CustomerModel>> exchange = customers.list().execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals("Firstname", ((CustomerModel) exchange.body().get(0)).getFirstname().get());
    }

    @Test
    public void testCanCreateAndGetCustomer() throws Exception {
        BigInteger id = customers.post(new CreateCustomer("Firstname", "Lastname")).execute().body().getId();
        Response<CustomerModel> exchange = customers.get(id).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals("Firstname", exchange.body().getFirstname().get());
    }

    @Test
    public void testCanCreateProductsAndListThem() throws Exception {
        products.post(new AddProduct(10, "product1")).execute();
        products.post(new AddProduct(20, "product2")).execute();
        Response<List<ProductModel>> exchange = products.list().execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertTrue(exchange.body().size() >= 2);
    }

    @Test
    public void testCanCreateProduct2sAndGetIt() throws Exception {
        BigInteger id = products.post(new AddProduct2()
                .cost(new BigDecimal(10))
                .name("product1")
                .properties(Map.of(
                        "Weight", "0.92kg",
                        "Length", "250cm",
                        "Width", "150cm")))
                .execute().body().getId();
        Response<ProductModel> exchange = products.get(id).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals("product1", exchange.body().getName().get());
        ProductModelV2 model=(ProductModelV2)exchange.body();
        assertTrue(model.getProperties().isPresent()); 
    }

    @Test
    public void testCanCreateAndGetProduct() throws Exception {
        BigInteger id = products.post(new AddProduct(10, "product1")).execute().body().getId();
        Response<ProductModel> exchange = products.get(id).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals("product1", exchange.body().getName().get());
    }

    @Test
    public void testCanCreateAndGetOrder() throws Exception {
        BigInteger customerId = customers.post(new CreateCustomer("Firstname", "Lastname")).execute().body().getId();
        Response<OrderModel> orderResponse = orders.post(new AddOrder(customerId)).execute();
        assertEquals(HttpStatus.OK.value(), orderResponse.code());
        BigInteger orderId = orderResponse.body().getId();
        BigInteger productId = products.post(new AddProduct(10, "product1")).execute().body().getId();
        Response<OrderModel> productAddedResponse = orders
                .addProduct(orderId, new AddProductToOrder().productId(productId)).execute();
        assertEquals(HttpStatus.OK.value(), productAddedResponse.code());
        Response<OrderModel> exchange = orders.get(orderId).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        OrderModel body = exchange.body();
        assertNotNull(body);
        assertEquals("Firstname", body.getCustomer().getFirstname().get());
        assertEquals(1, body.getProducts().get().size());
    }
}