package uk.ac.glasgow.jagora;

public class SEProxy implements StockExchangeOrders{
	StockExchange stockExchange;
	
	public SEProxy(StockExchange stockExchange){
		this.stockExchange = stockExchange;
	}
	
	
	@Override
	public void proxyPlaceBuyOrder(BuyOrder buyOrder) {
		stockExchange.placeBuyOrder(buyOrder);
		
	}

	@Override
	public void proxyPlaceSellOrder(SellOrder sellOrder) {
		stockExchange.placeSellOrder(sellOrder);
		
	}

	@Override
	public void proxyCancelBuyOrder(BuyOrder buyOrder) {
		stockExchange.cancelBuyOrder(buyOrder);
		
	}

	@Override
	public void proxyCancelSellOrder(SellOrder sellOrder) {
		stockExchange.cancelSellOrder(sellOrder);
		
	}


	@Override
	public Double proxygetBestOffer(Stock stock) {
		return stockExchange.getBestOffer(stock);
	}


	@Override
	public Double proxygetBestBid(Stock stock) {
		return stockExchange.getBestBid(stock);
	}

}
