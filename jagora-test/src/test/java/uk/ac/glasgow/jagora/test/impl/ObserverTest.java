package uk.ac.glasgow.jagora.test.impl;

import org.junit.Before;
import org.junit.Test;
import uk.ac.glasgow.jagora.*;
import uk.ac.glasgow.jagora.impl.DefaultStockExchange;
import uk.ac.glasgow.jagora.impl.DefaultTrader;
import uk.ac.glasgow.jagora.impl.DefaultWorld;
import uk.ac.glasgow.jagora.test.stub.StubBuyOrder;
import uk.ac.glasgow.jagora.test.stub.StubSellOrder;
import uk.ac.glasgow.jagora.test.stub.StubStock;
import uk.ac.glasgow.jagora.test.stub.StubTrader;
import static org.junit.Assert.assertTrue;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Admin on 24/03/2016.
 */


public class ObserverTest {
    DefaultTrader traderA;
    DefaultTrader traderB;
    DefaultStockExchange stockExchange;
    Map<Stock,Integer> traderInventory;
    Stock stubStock;


    @Before
    public void setUp(){
        traderInventory = new HashMap<>();
        stubStock = new StubStock();
        traderInventory.put(stubStock,50);
        traderA = new DefaultTrader("stub",5000.0,traderInventory);
        traderB = new DefaultTrader("stub",50.0,traderInventory);
        stockExchange = new DefaultStockExchange(new DefaultWorld());
        stockExchange.register(traderA,stubStock);
    }

    @Test
    public void testNotify() throws TradeException {
        traderA.buyStock(stubStock,25,10.0);
        traderB.sellStock(stubStock,25,5.0);
        stockExchange.doClearing();
        assertTrue(stockExchange.getTradeHistory(stubStock).equals(traderA.getNotifictation()));
    }
}
