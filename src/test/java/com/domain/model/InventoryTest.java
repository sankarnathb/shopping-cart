package com.domain.model;

import com.domain.filter.BuyOneGetOneFilter;
import com.domain.filter.FlatDiscountFilter;
import com.domain.filter.MarkedPriceFilter;
import com.domain.filter.PriceDiscountFilter;
import com.domain.filter.PromotionType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.*;

public class InventoryTest {

    private CopyOnWriteArrayList<Item> items = new CopyOnWriteArrayList<Item>();
    private CopyOnWriteArrayList<PromotionType> itemPromotions = new CopyOnWriteArrayList<PromotionType>();

    @Test
    public void testCreateInventory() {
    	List list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(2);
        p.setType("BUY_1_GET_1");
        p.setFreeQuantity(1);
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
        items.add(new Item("Veggie Delite Salad", 10, 3, list2));
        items.add(new Item("Thumps Up", 2, 6, null));
        items.add(new Item("Mexican Pizza", 10, 1, list3));

        Inventory inventory = new Inventory(items);

        assertNotNull(inventory);

        assertNotNull(inventory.getFilter());
        assertTrue(inventory.getFilter().get("Sprite") instanceof BuyOneGetOneFilter);
        assertTrue(inventory.getFilter().get("Veggie Delite Salad") instanceof FlatDiscountFilter);
        assertTrue(inventory.getFilter().get("Thumps Up") instanceof MarkedPriceFilter);
        assertTrue(inventory.getFilter().get("Mexican Pizza") instanceof PriceDiscountFilter);

        assertNotNull(inventory.getListingItems());
        assertEquals(inventory.getListingItems().get("Sprite").getName(), "Sprite");
        assertEquals(inventory.getListingItems().get("Sprite").getPrice(), 3.0d, 0.01);
        assertEquals(inventory.getListingItems().get("Veggie Delite Salad").getName(), "Veggie Delite Salad");
        assertEquals(inventory.getListingItems().get("Veggie Delite Salad").getPrice(), 10.0d, 0.01);


      
    }
}
