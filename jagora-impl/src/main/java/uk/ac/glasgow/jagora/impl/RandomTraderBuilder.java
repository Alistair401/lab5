package uk.ac.glasgow.jagora.impl;

import java.util.Map;
import java.util.Random;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.Trader;

public class RandomTraderBuilder{
	private String name;
    private double cash;
    private Random random;
	private Integer maxTradeQuantity;
	private Double priceRange;
    private Map<Stock,Integer> inventory;
    
    public RandomTraderBuilder(){
    }
    
    public RandomTraderBuilder name(String newName){
    	this.name = newName;
		return this;
    }
    
    public RandomTraderBuilder cash(double newCash){
    	this.cash = newCash;
    	return this;
    }
    
    public RandomTraderBuilder inventory(Map<Stock,Integer> newInventory){
    	this.inventory = newInventory;
    	return this;
    }

    
    public Trader createTrader(){
    	return new RandomTrader(name,cash, inventory, maxTradeQuantity, priceRange, random);
    }

}