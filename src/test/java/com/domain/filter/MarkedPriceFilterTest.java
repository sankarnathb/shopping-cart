package com.domain.filter;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;
import com.domain.model.Promotions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class MarkedPriceFilterTest {

    private MarkedPriceFilter markedPriceFilter;
    
    private List<Promotions> list;

    @Test
    public void testMarkedPrice() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        list=null;
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Thumps Up", 2, 6, null));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 2.0d, 0.15);
    }

    @Test
    public void testMarkedPriceWithEmptyList() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 0.0d, 0.00);
    }

    @Test
    public void testMarkedPriceWithManyNonePromotedItem() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Thumps Up", 2, 6, null));
        threadSafeItemList.add(new Item("Thumps Up", 2, 6, null));
        threadSafeItemList.add(new Item("Thumps Up", 2, 6, null));
        threadSafeItemList.add(new Item("Thumps Up", 2, 6, null));

        assertEquals(markedPriceFilter.filterPrice(threadSafeItemList), 8.0d, 6.00);
    }

    @Test(expected = ItemNotSameTypeException.class)
    public void testMarkedPriceWithException() throws ItemNotSameTypeException {
        markedPriceFilter = new MarkedPriceFilter();
        CopyOnWriteArrayList<Item> threadSafeItemList = new CopyOnWriteArrayList<Item>();
        threadSafeItemList.add(new Item("Thumps Up", 2, 6, null));
        threadSafeItemList.add(new Item("Sprite", 1, 0, null));
        markedPriceFilter.filterPrice(threadSafeItemList);
    }

}