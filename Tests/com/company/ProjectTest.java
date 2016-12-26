package com.company;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by kirill on 23.12.2016.
 */
public class ProjectTest {
    private Project project = new Project("test_project");
    private ProductsStorage storage = ProductsStorage.getInstance();
    @Before
    public void setUp() throws Exception {
        Recipe recipe1 = new Recipe("test_recipe_1");
        storage.addToStorage("fire",6);
        storage.addToStorage("earth",3);
        recipe1.addComponent("fire",2);
        recipe1.addComponent("earth",1);
        recipe1.addProduct("lava",2);
        project.addAbility(recipe1,5);

        Recipe recipe2 = new Recipe("test_recipe_2");
        storage.addToStorage("wood",6);
        storage.addToStorage("steel",3);
        recipe2.addComponent("wood",4);
        recipe2.addComponent("steel",1);
        recipe2.addProduct("arrow",5);
        project.addAbility(recipe2,4);


    }

    @Test
    public void production() throws Exception {
        assertEquals((Integer)19, project.production("factory 1").productionTime);
    }

}