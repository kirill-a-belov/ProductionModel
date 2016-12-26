package com.company;

import java.util.ArrayList;

/**
 * Created by kirill on 24.12.2016.
 */
public class Lot {
        public Integer productionTime;
        public ArrayList<Product> productsPool= new ArrayList<>();

        private Integer find(String name){
            for (int i = 0; i < productsPool.size() ; i++) {
                if (productsPool.get(i).name == name)
                    return  i;
            }
            return -1;
        }

        public void addToLot (String name, Integer quantity){
            Integer productIndex = find(name);
            if (productIndex >=0) {
                productsPool.get(productIndex).quantity += quantity;
            } else {
                productsPool.add(new Product(name, quantity));
            }
        }

}
