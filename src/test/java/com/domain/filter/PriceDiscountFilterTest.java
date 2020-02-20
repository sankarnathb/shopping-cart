package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import com.domain.model.Promotions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class PriceDiscountFilterTest {

    private PriceDiscountFilter priceDiscountFilter;
    
    private List<Promotions> list;

    @Test
    public void testPriceDiscount() throws ItemNotSameTypeException {
    	priceDiscountFilter = new PriceDiscountFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(2);
        p.setId(1);
        p.setType("PRICE_DISCOUNT");
        p.setPrice(18);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Mexican Pizza", 10, 1, list));
        threadSafeItemList.add(new Item("Mexican Pizza", 10, 1, list));
        assertEquals(priceDiscountFilter.filterPrice(threadSafeItemList), 18.0d, 0.01);
    }

    @Test
    public void testPriceDiscountWithEmptyList() throws ItemNotSameTypeException {
        priceDiscountFilter = new PriceDiscountFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(priceDiscountFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testPriceDiscountWithOneItem() throws ItemNotSameTypeException {
    	priceDiscountFilter = new PriceDiscountFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(2);
        p.setId(1);
        p.setType("PRICE_DISCOUNT");
        p.setPrice(18);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Mexican Pizza", 10, 1, list));

        assertEquals(priceDiscountFilter.filterPrice(threadSafeItemList), 10.0d, 0.01);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testPriceDiscountWithException() throws ItemNotSameTypeException {
    	priceDiscountFilter = new PriceDiscountFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(2);
        p.setId(1);
        p.setType("PRICE_DISCOUNT");
        p.setPrice(18);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Mexican Pizza", 10, 1, list));
        threadSafeItemList.add(new Item("Chicken Burger", 15, 2, list));
        priceDiscountFilter.filterPrice(threadSafeItemList);
    }

}