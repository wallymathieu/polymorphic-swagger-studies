package se.gewalli.polymorphic_swagger.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import org.openapitools.jackson.nullable.JsonNullableModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;
import se.gewalli.polymorphic_swagger.OpenApiGeneratorApplication;

public class ApplicationUnderTest extends OpenApiGeneratorApplication {

}

@Configuration
@Lazy
class TestConfig {
    @Autowired
    private Retrofit retrofit;

    @Bean
    public Customers getCustomers() {
        return retrofit.create(Customers.class);
    }

    @Bean
    public Products getProducts() {
        return retrofit.create(Products.class);
    }

    @Bean
    public Orders getOrders() {
        return retrofit.create(Orders.class);
    }
}

@Configuration
class RetrofitConfig {

    @Lazy
    @Bean
    public Retrofit getRetrofit(@LocalServerPort int port) {
        ObjectMapper objectMapper = new ObjectMapper()
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(new JsonNullableModule());
        objectMapper
                .setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return new Retrofit.Builder()
                .baseUrl("http://localhost:" + port)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .build();
    }
}