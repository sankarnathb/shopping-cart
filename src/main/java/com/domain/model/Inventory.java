package com.domain.model;

import com.domain.filter.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.IntStream;

public class Inventory {

    private ConcurrentMap<String, Item> listingItems = new ConcurrentHashMap<String, Item>();
    private ConcurrentMap<String, PromotionType> promotions = new ConcurrentHashMap<String, PromotionType>();
    private ConcurrentMap<String, AbstractFilter> filter = new ConcurrentHashMap<String, AbstractFilter>();
    private MarkedPriceFilter markedPriceFilter;
    private BuyOneGetOneFilter buyOneGetOneFilter;
    private PriceDiscountFilter priceDiscountFilter;
    private FlatDiscountFilter  flatDiscountFilter;


    public ConcurrentMap<String, Item> getListingItems() {
        return listingItems;
    }

    public ConcurrentMap<String, PromotionType> getPromotions() {
        return promotions;
    }

    public ConcurrentMap<String, AbstractFilter> getFilter() {
        return filter;
    }

    public AbstractFilter defaultNormalHelper() {
        return new MarkedPriceFilter();
    }

    public Inventory(List<Item> items) {

        markedPriceFilter = new MarkedPriceFilter();
        buyOneGetOneFilter = new BuyOneGetOneFilter();
        priceDiscountFilter = new PriceDiscountFilter();
        flatDiscountFilter = new FlatDiscountFilter();

        IntStream.range(0, items.size()).forEach(i -> {
            Item item = items.get(i);

            listingItems.put(item.getName(), item);
            if(item.getPromotions()==null) {
                filter.put(item.getName(), markedPriceFilter);
            }else if (item.getPromotions().get(0).getType().equalsIgnoreCase(PromotionType.BUY_1_GET_1.toString())) {
                filter.put(item.getName(), buyOneGetOneFilter);
            } else if (item.getPromotions().get(0).getType().equalsIgnoreCase(PromotionType.PRICE_DISCOUNT.toString())) {
                filter.put(item.getName(), priceDiscountFilter);
            } else if (item.getPromotions().get(0).getType().equalsIgnoreCase(PromotionType.FLAT_DISCOUNT.toString())) {
                filter.put(item.getName(), flatDiscountFilter);
            }else {
                filter.put(item.getName(), markedPriceFilter);
            }
        });
    }
}