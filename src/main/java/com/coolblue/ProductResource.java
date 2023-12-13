package com.coolblue;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/products")
public class ProductResource {
    @Inject
    ProductEmitter emitter;

    ProductRepository repository;

    ProductResource(ProductRepository repository) {
        this.repository = repository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> list() {
        return repository.listAll();
    }



    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Product addProduct(Product product) {
        repository.persist(product);

        emitter.productAdded(product);
        return product;
    }
}
