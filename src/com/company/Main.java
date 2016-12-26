package com.company;

public class Main {






    public static void main(String[] args) {
        ProductsStorage productsStorage = ProductsStorage.getInstance();
        Building building = Configuration.getBuilding();

       building.letsGo();
       // Configuration.parse();

    }
}
