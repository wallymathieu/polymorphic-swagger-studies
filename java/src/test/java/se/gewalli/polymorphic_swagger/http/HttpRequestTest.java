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
import se.gewalli.polymorphic_swagger.model.AddProductToOrder;
import se.gewalli.polymorphic_swagger.model.CreateCustomer;
import se.gewalli.polymorphic_swagger.model.CustomerModel;
import se.gewalli.polymorphic_swagger.model.OrderModel;
import se.gewalli.polymorphic_swagger.model.ProductModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals("Firstname", ((CustomerModel) exchange.body().get(0)).getFirstname());
    }

    @Test
    public void testCanCreateAndGetCustomer() throws Exception {
        customers.post(new CreateCustomer("Firstname", "Lastname")).execute();
        Response<CustomerModel> exchange = customers.get(1).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals("Firstname", exchange.body().getFirstname());
    }

    @Test
    public void testCanCreateProductsAndListThem() throws Exception {
        products.post(new AddProduct(10, "product1")).execute();
        products.post(new AddProduct(20, "product2")).execute();
        Response<List<ProductModel>> exchange = products.list().execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals(2, exchange.body().size());
    }

    @Test
    public void testCanCreateAndGetProduct() throws Exception {
        products.post(new AddProduct(10, "product1")).execute();
        Response<ProductModel> exchange = products.get(1).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        assertEquals("product1", exchange.body().getName());
    }

    @Test
    public void testCanCreateAndGetOrder() throws Exception {
        int customerId = customers.post(new CreateCustomer("Firstname", "Lastname")).execute().body().getId();
        int orderId = orders.post(new AddOrder(customerId)).execute().body().getId();
        int productId = products.post(new AddProduct(10, "product1")).execute().body().getId();
        Response<OrderModel> productAddedResponse = orders.addProduct(orderId, new AddProductToOrder().productId(productId)).execute();
        assertEquals(HttpStatus.OK.value(), productAddedResponse.code());
        Response<OrderModel> exchange = orders.get(orderId).execute();
        assertEquals(HttpStatus.OK.value(), exchange.code());
        OrderModel body = exchange.body();
        assertNotNull(body);
        assertEquals("Firstname", body.getCustomer().getFirstname());
        assertEquals(1, body.getProducts().get().size());
    }
}