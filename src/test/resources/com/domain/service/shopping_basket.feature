Feature: Shopping Basket Test

  Scenario: shopping basket has 4 items
    Given The shopping basket has 1 Mexican Pizza and 1 Chicken Burger and 1 Coke
    When I calculate the final price
    Then Total bill payable show 28.0
    Then Total Promos Applied show 0.0
    Then Total Payable Amount show 28.0

  Scenario: shopping basket has 8 items
    Given The shopping basket has 2 Mexican Pizza and 5 Paneer Tikka and 1 Thumps Up
    When I calculate the final price
    Then Total bill payable show 62.0
    Then Total Promos Applied show 2.0
    Then Total Payable Amount show 60.0
    

  Scenario: shopping basket has 7 items
    Given The shopping basket has 2 Mexican Pizza and 2 Chicken Burger and 2 Coke and 1 Veggie Delite Salad
    When I calculate the final price
    Then Total bill payable show 66.0
    Then Total Promos Applied show 22.0
    Then Total Payable Amount show 44.0
    

