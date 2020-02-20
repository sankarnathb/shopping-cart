package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import com.domain.model.Promotions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class BuyOneGetOneFilterTest {

    private BuyOneGetOneFilter buyOneGetOneFilter;
    
    private List<Promotions> list;

    @Test
    public void testBuyOneGetOne() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(2);
        p.setType("BUY_1_GET_1");
        p.setFreeQuantity(1);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Sprite", 3, 7, list));
        threadSafeItemList.add(new Item("Sprite", 3, 7, list));
        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList), 3.0d, 0.01);
    }

    @Test
    public void testBuyOneGetOneWithEmptyList() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testBuyOneGetOneWithOneItem() throws ItemNotSameTypeException {
    	buyOneGetOneFilter = new BuyOneGetOneFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(2);
        p.setType("BUY_1_GET_1");
        p.setFreeQuantity(1);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Sprite", 3, 7, list));

        assertEquals(buyOneGetOneFilter.filterPrice(threadSafeItemList), 3.0d, 0.01);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testBuyOneGetOneWithException() throws ItemNotSameTypeException {
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        list=new ArrayList<>();
        Promotions p= new Promotions();
        p.setRequiredQuantity(1);
        p.setId(2);
        p.setType("BUY_1_GET_1");
        p.setFreeQuantity(1);
        list.add(p);

        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Sprite", 3, 7, list));
        threadSafeItemList.add(new Item("Chicken Burger", 15, 2, list));
        buyOneGetOneFilter.filterPrice(threadSafeItemList);
    }

}