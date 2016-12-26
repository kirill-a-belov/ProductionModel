package com.company;

import java.util.ArrayList;

/**
 * Global products storage
 * Contains:
 * 1. Products list
 */
public class ProductsStorage {

    private volatile static ProductsStorage instance;
    private static ArrayList<Product> productsPool= new ArrayList<>();



    private ProductsStorage() {}
    public static ProductsStorage getInstance() {
        if (instance == null) {
            synchronized (ProductsStorage.class) {
                if (instance == null) {
                    instance = new ProductsStorage();
                }
            }
        }
        return instance;
    }
    //TODO: Make faster realisation (maybe table of products names and indexes)
    private Integer find(String name){
        for (int i = 0; i < productsPool.size() ; i++) {
            if (productsPool.get(i).name == name)
                return  i;
        }
         return -1;
    }

    public void addToStorage (String name, Integer quantity){
        Integer productIndex = find(name);
        if (productIndex >=0) {
            productsPool.get(productIndex).quantity += quantity;
        } else {
            productsPool.add(new Product(name, quantity));
        }
    }

    public Integer getFromStorage (String name, Integer quantity){
        Integer productIndex = find(name);
        if (productIndex >=0) {
           if ((productsPool.get(productIndex).quantity - quantity) >= 0 ){
               productsPool.get(productIndex).quantity-=quantity;
               return quantity;
           }
        }
        return -1;
    }
    public Integer checkInStorage (String name){
        Integer productIndex = find(name);
        if (productIndex >=0) {
            return productsPool.get(productIndex).quantity;
        }
        return -1;
    }

    //TODO: Delete it, temporary function
    public static void print(){
        System.out.println("Storage state:");
        for (Product elementTotal : productsPool) {
            System.out.println(elementTotal.name+" - " + elementTotal.quantity);
        }
    }


}