package uk.ac.glasgow.jagora.impl;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.StockExchangeOrders;

public class SEProxy implements StockExchangeOrders{
	StockExchangeOrders stockExchange;
	
	public SEProxy(StockExchangeOrders stockExchange){
		this.stockExchange = stockExchange;
	}
	
	
	@Override
	public void proxyPlaceBuyOrder(BuyOrder buyOrder) {
		stockExchange.proxyPlaceBuyOrder(buyOrder);
	}

	@Override
	public void proxyPlaceSellOrder(SellOrder sellOrder) {
		stockExchange.proxyPlaceSellOrder(sellOrder);
		
	}

	@Override
	public void proxyCancelBuyOrder(BuyOrder buyOrder) {
		stockExchange.proxyCancelBuyOrder(buyOrder);
		
	}

	@Override
	public void proxyCancelSellOrder(SellOrder sellOrder) {
		stockExchange.proxyCancelSellOrder(sellOrder);
		
	}


	@Override
	public Double proxygetBestOffer(Stock stock) {
		return stockExchange.proxygetBestOffer(stock);
	}


	@Override
	public Double proxygetBestBid(Stock stock) {
		return stockExchange.proxygetBestBid(stock);
	}

}
