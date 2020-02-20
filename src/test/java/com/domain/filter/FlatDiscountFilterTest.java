package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import com.domain.model.Promotions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class FlatDiscountFilterTest {

    private FlatDiscountFilter flatDiscountFilter;
    
    private List<Promotions> list;

    @Test
    public void testFlatDiscount() throws ItemNotSameTypeException {
        flatDiscountFilter = new FlatDiscountFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(3);
        p.setType("FLAT_DISCOUNT");
        p.setPrice(2);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Veggie Delite Salad", 10, 3, list));
        threadSafeItemList.add(new Item("Veggie Delite Salad", 10, 3, list));
        assertEquals(flatDiscountFilter.filterPrice(threadSafeItemList), 16.0d, 0.01);
    }

    @Test
    public void testFlatDiscountWithEmptyList() throws ItemNotSameTypeException {
        flatDiscountFilter = new FlatDiscountFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(flatDiscountFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testFlatDiscountWithOneItem() throws ItemNotSameTypeException {
    	flatDiscountFilter = new FlatDiscountFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(3);
        p.setType("FLAT_DISCOUNT");
        p.setPrice(2);
        list.add(p);


        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Veggie Delite Salad", 10, 3, list));

        assertEquals(flatDiscountFilter.filterPrice(threadSafeItemList), 8.0d, 0.01);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testFlatDiscountWithException() throws ItemNotSameTypeException {
        flatDiscountFilter = new FlatDiscountFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(3);
        p.setType("FLAT_DISCOUNT");
        p.setPrice(2);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Veggie Delite Salad", 10, 3, list));
        threadSafeItemList.add(new Item("Chicken Burger", 15, 2, list));
        flatDiscountFilter.filterPrice(threadSafeItemList);
    }

}