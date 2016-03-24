package uk.ac.glasgow.jagora.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchangeOrders;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;

/**
 * Implements the behaviour of a passive default trader who never makes bids or
 * offers on the market.
 * 
 * @author tws
 *
 */
public class DefaultTrader extends AbstractTrader {
	
	public DefaultTrader(String name, Double cash, Stock stock, Integer quantity) {
		super(name, cash, createInventory(stock, quantity));
	}
	
	public DefaultTrader(String name, Double cash,Map<Stock,Integer> inventory) {
		super(name, cash, inventory);
	}

	private static Map<Stock, Integer> createInventory(Stock stock, int quantity) {
		Map<Stock,Integer> inventory = new HashMap<Stock,Integer>();
		inventory.put(stock, quantity);
		return inventory;
	}

	@Override
	public void speak(StockExchangeOrders stockExchange) {
		//Does nothing.
	}

	@Override
	public List<TickEvent<Trade>> notify(List<TickEvent<Trade>> trades) {
		return trades;
	}
}
