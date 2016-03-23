package uk.ac.glasgow.jagora.impl;

import java.util.Map;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.Trader;
import uk.ac.glasgow.jagora.TraderBuilder;

public class DefaultTraderBuilder implements TraderBuilder{
	private String name;
    private Map<Stock,Integer> inventory;
    private double cash;
    
    public DefaultTraderBuilder(){
    }
    
    public DefaultTraderBuilder name(String newName){
    	this.name = newName;
		return this;
    }
    
    public DefaultTraderBuilder cash(double newCash){
    	this.cash = newCash;
    	return this;
    }
    
    public DefaultTraderBuilder inventory(Map<Stock,Integer> newInventory){
    	this.inventory = newInventory;
    	return this;
    }

    
    public Trader createTrader(){
    	return new DefaultTrader(name,cash,inventory);
    }
}