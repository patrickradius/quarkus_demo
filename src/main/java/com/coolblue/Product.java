package com.coolblue;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.UUID;


@Entity
public class Product {
    @Id
    @GeneratedValue
    public UUID id;
    public String name;
    public Double price;
}
