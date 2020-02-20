package com.domain.filter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.domain.exception.ItemNotSameTypeException;
import com.domain.model.Item;

public class FlatDiscountFilter extends AbstractFilter {

	 public double filterPrice(List<Item> items) throws ItemNotSameTypeException {
	        super.filterItemsBeforeCalculatePrice(items);

	        if (items.size() == 0) {
	            return 0;
	        }

	        return items.size() * (items.stream().findFirst().get().getPrice()-items.stream().findFirst().get().getPromotions().stream().findFirst().get().getPrice());
	    }

}
