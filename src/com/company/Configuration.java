package com.company;


import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

/**
 * Created by kirill on 23.12.2016.
 */

public class Configuration {
    public static Map<String, Map<String, Map<String, String>>> environment;



    private static Building building = new Building();
    private static  ProductsStorage storage = ProductsStorage.getInstance();
    public static Building getBuilding(){


        storage.addToStorage("fire",5);
        storage.addToStorage("earth",3);

        Recipe recipe1 = new Recipe("recipe1");
        recipe1.addComponent("fire",1);
        recipe1.addProduct("water",1);
        Recipe recipe2 = new Recipe("recipe2");
        recipe2.addComponent("earth",1);
        recipe2.addComponent("water",1);
        recipe2.addProduct("mud",1);

        Project project1 = new Project("project1");
        project1.addAbility(recipe1,3);
        Project project2 = new Project("project2");
        project2.addAbility(recipe2,2);

        building.addFactory("factory1",project1);
        building.addFactory("factory2",project2);

        return building;
    }

    public static void parse(){
        try {
            InputStream in = Files.newInputStream(Paths.get("src/input.yml"));
            Yaml yaml = new Yaml();
            environment = (Map<String, Map<String, Map<String, String>>>)yaml.load(in);
           /* Map<String, Map<String, String>> products = environment.get("products");
            Map<String, Map<String, String>> recipes = environment.get("recipes");
            Map<String, Map<String, String>> projects = environment.get("projects");
            Map<String, Map<String, String>> buildings = environment.get("buildings");
            System.out.println(products);
            System.out.println();
            System.out.println(recipes);
            System.out.println();
            System.out.println(projects);
            System.out.println();
            System.out.println(buildings);*/

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
