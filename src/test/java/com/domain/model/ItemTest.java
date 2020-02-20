package com.domain.model;

import com.domain.exception.ItemNotSameTypeException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

public class ItemTest {

    private static final double DELTA = 1e-15;

    @Test
    public void createItem() {
        Item item = new Item("Sprite", 3, 7, null);
        assertEquals(item.getName(), "Sprite");
        assertEquals(item.getPrice(), 3.0, 0.01);
    }

    @Test
    public void addItem() throws ItemNotSameTypeException {
    	List<Promotions>  list=new ArrayList<>();
           Promotions p= new Promotions();
           p.setRequiredQuantity(1);
           p.setId(2);
           p.setType("BUY_1_GET_1");
           p.setFreeQuantity(1);
           list.add(p);

        Item item = new Item("Sprite", 3, 7, list);
        Item newItem = new Item(item);
        newItem.setName("ThumpsUp");
        newItem.setPrice(0.1);
        assertEquals(newItem.getName(), "ThumpsUp");
        assertEquals(newItem.getPrice(), 0.1, DELTA);

    }
}