package com.company;

import java.util.HashMap;

/**
 * Production recipe
 * Contains:
 * 1. Recipe name
 * 2. List of input components (products)
 * 3. List of output components (products)
 */
public class Recipe {

    public String name;
    //<product name, product quantity>
    public HashMap<String,Integer> components = new HashMap<String,Integer>();
    public Product product;

    public Recipe(String name){
        this.name = name;
    }
    public void addComponent(String name, Integer quantity){
        components.put(name, quantity);
    }
    public void addProduct(String name, Integer quantity){
        this.product = new Product(name,quantity);
    }

}
