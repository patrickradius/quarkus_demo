package com.coolblue;

import org.eclipse.microprofile.reactive.messaging.*;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CoolbluesKafka implements ProductEmitter {

    @Inject
    @Channel("products")
    Emitter<Product> productEmitter;

    public void productAdded(Product product) {
        productEmitter.send(product);
    }
}
