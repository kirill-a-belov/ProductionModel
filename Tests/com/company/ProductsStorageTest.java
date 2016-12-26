package com.company;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by kirill on 23.12.2016.
 */
public class ProductsStorageTest {
   private ProductsStorage storage = ProductsStorage.getInstance();

    @Before
    public void setUp() throws Exception {
        storage.addToStorage("fire",5);
        storage.addToStorage("earth",3);
    }

    @Test
    public void checkInStorage() throws Exception {
        assertEquals((Integer)5, storage.checkInStorage("fire"));
        assertEquals((Integer)3, storage.checkInStorage("earth"));
    }

    @Test
    public void getFromStorage() throws Exception {
        assertEquals((Integer)4, storage.getFromStorage("fire",4));
        assertEquals((Integer)2, storage.getFromStorage("earth",2));
        assertEquals((Integer)(-1), storage.getFromStorage("fire",4));
        assertEquals((Integer)(-1), storage.getFromStorage("earth",2));
    }

    @After
    public void tearDown() throws Exception {
        storage.getFromStorage("fire",5);
        storage.getFromStorage("earth",3);

    }
}