package com.company;

import java.util.HashMap;
import java.util.Map;

/**
 * Production
 * Contains:
 * 1. Project name
 * 2. List of recipes and time for them production
 */


public class Project {

    public String name;
    //recipe name, recipe production duration>
    public HashMap<Recipe,Integer[]> abilities = new HashMap<Recipe,Integer[]>();

    public Project(String name){
        this.name = name;
    }
    public void addAbility(Recipe recipe, Integer duration){
        abilities.put(recipe,new Integer[]{duration,0});
    }

    private Boolean create(Recipe recipe){
        ProductsStorage storage = ProductsStorage.getInstance();
        synchronized (storage) {
            // System.out.println(recipe.name);
            for (Map.Entry<String, Integer> component : recipe.components.entrySet()) {
                if (storage.checkInStorage(component.getKey()) < component.getValue()) {
                    return false;
                }
            }
            for (Map.Entry<String, Integer> component : recipe.components.entrySet()) {
                storage.getFromStorage(component.getKey(), component.getValue());

            }
            return true;
        }
    }

    public Lot production(String factoryName){
        Lot lot = new Lot();
        lot.productionTime = 0;
        Integer avalible = abilities.size();
       // while (avalible >0) {
            for (Map.Entry<Recipe, Integer[]> element : abilities.entrySet()) {
                if (create(element.getKey())) {
                    lot.productionTime += element.getValue()[0];
                    element.getValue()[1]+=element.getKey().product.quantity;
                    System.out.println(
                            factoryName+
                                  //  "             "+lot.productionTime+
                                    "     "+element.getValue()[0]+
                                    "     "+element.getKey().name+
                                    "     "+element.getKey().product.name+
                                    "        "+element.getKey().product.quantity
                                  // + "             "+ element.getValue()[1]
                    );
                   ProductsStorage storage = ProductsStorage.getInstance();
                    storage.addToStorage(element.getKey().product.name,element.getKey().product.quantity);
                  lot.addToLot(element.getKey().product.name,element.getKey().product.quantity);
                } else {
                    avalible -= 1;
                }
            }
       // }
        return lot;

    }




}

