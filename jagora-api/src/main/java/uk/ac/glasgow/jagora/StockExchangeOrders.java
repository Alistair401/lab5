package uk.ac.glasgow.jagora;

public interface StockExchangeOrders {
	public void proxyPlaceBuyOrder(BuyOrder buyOrder);
	public void proxyPlaceSellOrder(SellOrder sellOrder);
	public void proxyCancelBuyOrder(BuyOrder buyOrder);
	public void proxyCancelSellOrder(SellOrder sellOrder);
	public Double proxygetBestOffer(Stock stock);
	public Double proxygetBestBid(Stock stock);
}
