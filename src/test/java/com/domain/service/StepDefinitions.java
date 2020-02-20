package com.domain.service;

import com.domain.filter.PromotionType;
import com.domain.model.Inventory;
import com.domain.model.Item;
import com.domain.model.Promotions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class StepDefinitions {
    private Cart cart;
    private List<Item> items = new CopyOnWriteArrayList<Item>();
    private Inventory inventory;

    @Before
    public void setUp() {
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
        items.add(new Item("Coke", 3, 8, list));
        items.add(new Item("Veggie Delite Salad", 10, 3, list2));
        items.add(new Item("Thumps Up", 2, 6, null));
        items.add(new Item("Paneer Tikka", 8, 5, null));
        items.add(new Item("Potato Fries", 6, 4, null));
        items.add(new Item("Mexican Pizza", 10, 1, list3));
        items.add(new Item("Chicken Burger", 15, 2, list));

        

         inventory = new Inventory(items);
    }

    @Given("^The shopping basket has (\\d+) Mexican Pizza and (\\d+) Chicken Burger and (\\d+) Coke$")
    public void the_shopping_basket_has_Mexican_Pizza_and_Chicken_Burger_and_Coke(int arg1, int arg2, int arg3) throws Throwable {
    	  List<String> addItems = Arrays.asList("Mexican Pizza", "Chicken Burger", "Coke");

          cart = new Cart(inventory);
          cart.add(addItems);
    }

    @Given("^The shopping basket has (\\d+) Mexican Pizza and (\\d+) Paneer Tikka and (\\d+) Thumps Up$")
    public void the_shopping_basket_has_Mexican_Pizza_and_Paneer_Tikka_and_Thumps_Up(int arg1, int arg2, int arg3) throws Throwable {
        List<String> addItems = Arrays.asList("Mexican Pizza", "Mexican Pizza","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka","Paneer Tikka", "Thumps Up");

        cart = new Cart(inventory);
        cart.add(addItems);
    }
    

    @Given("^The shopping basket has (\\d+) Mexican Pizza and (\\d+) Chicken Burger and (\\d+) Coke and (\\d+) Veggie Delite Salad$")
    public void the_shopping_basket_has_Mexican_Pizza_and_Chicken_Burger_and_Coke_and_Veggie_Delite_Salad(int arg1, int arg2, int arg3, int arg4) throws Throwable {
        List<String> addItems = Arrays.asList("Mexican Pizza","Mexican Pizza", "Chicken Burger", "Chicken Burger","Coke","Coke","Veggie Delite Salad");

        cart = new Cart(inventory);
        cart.add(addItems);
    }



    @When("^I calculate the final price$")
    public void i_calculate_the_final_price() throws Throwable {
        cart.calculateFinalPrice();
    }

    @Then("^Total bill payable show (\\d+)\\.(\\d+)$")
    public void total_bill_payable_show(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(cart.calculateMarkerPrice(), arg1, 0.01);
    }

    @Then("^Total Promos Applied show (\\d+)\\.(\\d+)$")
    public void total_Promos_Applied_show(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(cart.calculateMarkerPrice()-cart.calculateFinalPrice(), arg1, 0.01);
    }

    @Then("^Total Payable Amount show (\\d+)\\.(\\d+)$")
    public void total_Payable_Amount_show(int arg1, int arg2) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(cart.calculateFinalPrice(), arg1, 0.01);
    }
 
    
}
