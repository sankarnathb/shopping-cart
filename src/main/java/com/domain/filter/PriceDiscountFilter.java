package com.domain.filter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

public class PriceDiscountFilter extends AbstractFilter{

	 public double filterPrice(List<Item> items) throws ItemNotSameTypeException {
	        super.filterItemsBeforeCalculatePrice(items);

	        if (items.size() == 0) {
	            return 0;
	        }

	        AtomicInteger numberOfGroup = new AtomicInteger(items.size() / 2);
	        AtomicInteger numberRemain = new AtomicInteger(items.size() % 2);
	        
	        return numberOfGroup.get() * items.get(0).getPromotions().get(0).getPrice() + numberRemain.get() * items.get(0).getPrice();

	    }
}
