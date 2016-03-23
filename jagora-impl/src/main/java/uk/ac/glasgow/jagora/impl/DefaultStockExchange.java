package uk.ac.glasgow.jagora.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.Market;
import uk.ac.glasgow.jagora.Observer;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.Subject;
import uk.ac.glasgow.jagora.TickEvent;
import uk.ac.glasgow.jagora.Trade;
import uk.ac.glasgow.jagora.World;

public class DefaultStockExchange implements StockExchange,Subject {

	private final Map<Stock,Market> markets;
	private World world;
	private final List<TickEvent<Trade>> tradeHistory;
	private HashMap<Stock,List<Observer>> observers;
	private List<TickEvent<Trade>> clearingLog;
	
	public DefaultStockExchange(World world){
		observers = new HashMap<Stock,List<Observer>>(); 
		this.world = world;
		markets = new HashMap<Stock,Market>();
		tradeHistory = new ArrayList<TickEvent<Trade>>();
	}
	
	@Override
	public void doClearing() {
		for (Market market: markets.values()){
			clearingLog = market.doClearing();
			tradeHistory.addAll(clearingLog);
			notifyObserver(market,clearingLog);
		}
		
		
		
	}

	@Override
	public void placeBuyOrder(BuyOrder buyOrder) {
		getMarket(buyOrder.getStock()).placeBuyOrder(buyOrder);
	}

	@Override
	public void placeSellOrder(SellOrder sellOrder) {
		getMarket(sellOrder.getStock()).placeSellOrder(sellOrder);
	}

	@Override
	public void cancelBuyOrder(BuyOrder buyOrder) {
		getMarket(buyOrder.getStock()).cancelBuyOrder(buyOrder);
	}

	@Override
	public void cancelSellOrder(SellOrder sellOrder) {
		getMarket(sellOrder.getStock()).cancelSellOrder(sellOrder);
	}
	
	@Override
	public Double getBestOffer(Stock stock) {
		return getMarket(stock).getBestOffer();
	}

	@Override
	public Double getBestBid(Stock stock) {
		return getMarket(stock).getBestBid();
	}
	
	private Market getMarket(Stock stock) {
		Market market = markets.get(stock);
		if (market == null){
			market = new ContinuousOrderDrivenMarket(stock, world);
			markets.put(stock, market);
		}
		return market;
	}

	@Override
	public List<TickEvent<Trade>> getTradeHistory(Stock stock) {
		return tradeHistory
			.stream()
			.filter(tradeEvent->tradeEvent.getEvent().getStock().equals(stock))
			.collect(Collectors.toList());
	}
	
	@Override
	public String toString (){
		StringBuffer result = new StringBuffer("[");
		String template = "%s:[bestBid=%.2f, bestOffer=%.2f], ";
		for (Stock stock : markets.keySet())
			result.append(String.format(template, stock.getName(), getBestOffer(stock), getBestBid(stock)));
			
		return result.delete(result.length()-2,result.length()).append("]").toString();
	}

	

	@Override
	public void register(Observer o, Stock s) {
		if (observers.containsKey(s)){
			observers.get(s).add(o);
		} else {
			observers.put(s, new ArrayList<Observer>());
			observers.get(s).add(o);
		}
		
	}

	@Override
	public void unregister(Observer o, Stock s) {
		if (observers.containsKey(s)){
			observers.get(s).remove(o);
		}	
	}

	@Override
	public void notifyObserver(Market market, List<TickEvent<Trade>> log) {
		Stock observedStock = market.getStock();
		if (!observers.isEmpty()){
			for(Observer observer: observers.get(observedStock)){
				observer.notify(log);
			}
		}
	}

}
