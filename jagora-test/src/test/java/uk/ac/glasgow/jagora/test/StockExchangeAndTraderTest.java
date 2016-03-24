package uk.ac.glasgow.jagora.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static uk.ac.glasgow.jagora.test.stub.StubStock.lemons;

import org.junit.Ignore;
import org.junit.Test;

import uk.ac.glasgow.jagora.BuyOrder;
import uk.ac.glasgow.jagora.SEProxy;
import uk.ac.glasgow.jagora.SellOrder;
import uk.ac.glasgow.jagora.StockExchange;
import uk.ac.glasgow.jagora.Trader;

@Ignore
public abstract class StockExchangeAndTraderTest {

	protected StockExchange stockExchange;

	protected BuyOrder badBuyOrder;
	protected SellOrder badSellOrder;
	
	protected SellOrder goodSellOrder;
	protected BuyOrder goodBuyOrder;

	protected Trader buyer;
	protected Trader seller;

	public StockExchangeAndTraderTest() {
		super();
	}

	@Test
	public void testBadBuyOrder() {
		stockExchange.placeBuyOrder(badBuyOrder);
		stockExchange.placeSellOrder(goodSellOrder);
		stockExchange.doClearing();
		assertNull(stockExchange.getBestBid(lemons));
		assertNotNull(stockExchange.getBestOffer(lemons));
		assertEquals(0, stockExchange.getTradeHistory(lemons).size());
	}
	
	@Test
	public void testBadSellOrder() {
		stockExchange.placeBuyOrder(goodBuyOrder);
		stockExchange.placeSellOrder(badSellOrder);
		stockExchange.doClearing();
		assertEquals(0, stockExchange.getTradeHistory(lemons).size());
		assertNull(stockExchange.getBestOffer(lemons));
		assertNotNull(stockExchange.getBestBid(lemons));
	}
	
	@Test(expected=NoSuchMethodException.class)
	public void testProxyApiForbiddenMethods()throws Exception{
		Class.forName("uk.ac.glasgow.jagora.SEProxy").getMethod("doClearing");
	}
	
	@Test
	public void testProxyApiAllowedMethods()throws Exception{
		Class.forName("uk.ac.glasgow.jagora.SEProxy").getMethod("proxyPlaceBuyOrder",uk.ac.glasgow.jagora.BuyOrder.class);
		Class.forName("uk.ac.glasgow.jagora.SEProxy").getMethod("proxyPlaceSellOrder",uk.ac.glasgow.jagora.SellOrder.class);
		Class.forName("uk.ac.glasgow.jagora.SEProxy").getMethod("proxyCancelBuyOrder",uk.ac.glasgow.jagora.BuyOrder.class);
		Class.forName("uk.ac.glasgow.jagora.SEProxy").getMethod("proxyCancelSellOrder",uk.ac.glasgow.jagora.SellOrder.class);
	}
	
	@Test
	public void testGetBidAndOffer(){
		stockExchange.placeBuyOrder(badBuyOrder);
		stockExchange.placeSellOrder(goodSellOrder);
		
		SEProxy seProxy = new SEProxy(stockExchange);
		Object resultBid = seProxy.proxygetBestBid(lemons);
		Object resultOffer = seProxy.proxygetBestOffer(lemons);
		assertEquals(true, resultBid instanceof Double);
		assertEquals(true, resultOffer instanceof Double);
	}
	

}