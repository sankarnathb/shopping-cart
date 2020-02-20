package com.domain.model;

import java.util.ArrayList;
import java.util.List;

import com.domain.exception.ItemNotSameTypeException;

public class Item {
	
	private int id;
	private String name;
    private double price;
    private List<Promotions> promotions= new ArrayList<>();

    public List<Promotions> getPromotions() {
		return promotions;
	}

	public void setPromotions(List<Promotions> promotions) {
		this.promotions = promotions;
	}

	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
    public Item(String name, double price, int id,List<Promotions> promotions) {
        this.name = name;
        this.price = price;
        this.id=id;
        this.promotions=promotions;
    }

    public Item(Item item) throws ItemNotSameTypeException {

        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null.");
        }

        this.name = item.name;
        this.price = item.getPrice();
        this.id=item.getId();
        this.promotions=item.getPromotions();
    }
}
