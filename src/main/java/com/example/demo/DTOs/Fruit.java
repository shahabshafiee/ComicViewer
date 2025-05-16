package com.example.demo.DTOs;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//@Entity
public class Fruit {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primary key (Automatically generated)

    private String fruit;

    // Default constructor (required by JPA)
    public Fruit() {
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFruit() {
        return fruit;
    }

    public void setFruit(String fruit) {
        this.fruit = fruit;
    }
}