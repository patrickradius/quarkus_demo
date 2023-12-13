package com.coolblue;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;

@QuarkusTest
class ProductResourceTest {

    @InjectMock
    ProductRepository productRepository;

    @Test
    void testListEndpointReturnsEmptyList() {
        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body(is("[]"));
    }

    @Test
    void testListEndpointReturnsItems() {
        Product p = new Product();
        p.name = "Foobar";

        Mockito.when(productRepository.listAll()).thenReturn(List.of(p));

        given()
                .when().get("/products")
                .then()
                .statusCode(200)
                .body("size()", is(1))
                .body("name", hasItem("Foobar"));
    }


    @Test
    void testPostEndpointPersists() {
        Product p = new Product();
        p.name = "Foobar";

        given()
                .body(p)
                .contentType("application/json")
                .when().post("/products")
                .then()
                .statusCode(200);

        Mockito
                .verify(productRepository, Mockito.atLeastOnce())
                .persist(Mockito.any(Product.class));
    }
}