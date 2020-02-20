# Post-Interview Exercise

## Overview
Implementation of a shopping cart in Java.

## Requirement
Items are presented one at a time, in a list, identified by name for example   Mexican Pizza or Chicken Burger or Coke. Multiple items are present multiple times in the list, so for example ["Mexican Pizza", "Chicken Burger", "Coke"] is a basket with  1 Mexican Pizza and 1 Chicken Burger and 1 Coke.
Items are priced as follows: 
- Sprite is 3.0 each, but available as ‘buy one get one free’
- Veggie Delite Salad are 10.0 each
- Thumps Up are 2.0 each etc according to given Json of products

Given a list of shopping, calculate the total cost of those items

## Test senarios

 Please see all three test cases in file shopping_basket.feature file

## Prerequisites
- Java 8
- maven 3

## Build
`mvn clean install`

## Test
`mvn clean test`

## BDD Test (Cucumber)
`mvn clean verify -Dit.test=ICartRunner -Dcucumber.options=" --format html:report/cucumber-html-report-myReport"`
