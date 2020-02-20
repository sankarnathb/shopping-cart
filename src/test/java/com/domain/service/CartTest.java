package com.domain.service;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.filter.PromotionType;
import com.domain.model.Inventory;
import com.domain.model.Item;
import com.domain.model.Promotions;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.*;

public class CartTest {

    private Inventory inventory;
    private List<Item> items = new CopyOnWriteArrayList<Item>();
    private List<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Before
    public void setUp() {
    	List list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(2);
        p.setType("BUY_1_GET_1");
        p.setFreeQuantity(1);
        list.add(p);
        
      	List list4=new ArrayList<>();
        list.add(p);
        
        
        Promotions p2= new Promotions();
        List list2=new ArrayList<>();
        p2.setRequiredQuantity(1);
        p2.setId(3);
        p2.setType("FLAT_DISCOUNT");
        p2.setPrice(2);
        list2.add(p2);
        
        Promotions p3= new Promotions();
        List list3=new ArrayList<>();
        p3.setRequiredQuantity(2);
        p3.setId(1);
        p3.setType("PRICE_DISCOUNT");
        p3.setPrice(18);
        list3.add(p3);

        items.add(new Item("Sprite", 3, 7, list));
        items.add(new Item("Coke", 3, 8, list));
        items.add(new Item("Veggie Delite Salad", 10, 3, list2));
        items.add(new Item("Thumps Up", 2, 6, null));
        items.add(new Item("Paneer Tikka", 8, 5, null));
        items.add(new Item("Potato Fries", 6, 4, null));
        items.add(new Item("Mexican Pizza", 10, 1, list3));
        items.add(new Item("Chicken Burger", 15, 2, list));

        

         inventory = new Inventory(items);
    }

    @Test
    public void testCreateBasket() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Mexican Pizza", "Chicken Burger", "Coke"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertNotNull(cart);
        assertTrue(cart.getItems().size() > 0);

    }

    @Test
    public void testCalculateFinalPrice() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Mexican Pizza","Mexican Pizza","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Thumps Up"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertEquals(cart.calculateFinalPrice(), 60.0d, 0.001);
    }

    @Test
    public void testCalculateMarkedPrice() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Mexican Pizza","Mexican Pizza","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Thumps Up"));

        Cart cart = new Cart(inventory);
        cart.add(order);

        assertEquals(cart.calculateMarkerPrice(), 62.0d, 0.001);
    }

    @Test
    public void testEmptyCart() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Mexican Pizza","Mexican Pizza","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Thumps Up"));

        Cart cart = new Cart(inventory);
        cart.add(order);
        cart.empty();

        assertEquals(cart.calculateMarkerPrice(), 0, 0.0);
        assertEquals(cart.calculateFinalPrice(), 0, 0.0);
    }



    @Test(expected = IllegalArgumentException.class)
    public void testCatchEmptyListOnAddException() throws ItemNotSameTypeException {
        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("", ""));
        Cart cart = new Cart(inventory);
        cart.add(order);
        cart.empty();
    }




    private synchronized String getRandomItem() {

        List<String> order = new CopyOnWriteArrayList<String>(Arrays.asList("Mexican Pizza","Mexican Pizza","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Thumps Up"));
        int rnd = new Random().nextInt(order.size());
        return order.get(rnd);
    }
}