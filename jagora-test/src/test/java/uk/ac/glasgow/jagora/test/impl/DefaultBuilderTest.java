package uk.ac.glasgow.jagora.test.impl;

import org.junit.Before;
import org.junit.Test;
import uk.ac.glasgow.jagora.Stock;
import uk.ac.glasgow.jagora.Trader;
import static org.junit.Assert.assertEquals;
import uk.ac.glasgow.jagora.impl.DefaultTraderBuilder;
import uk.ac.glasgow.jagora.test.stub.StubStock;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Alistair on 24/03/2016.
 */


public class DefaultBuilderTest {
    Trader trader;
    String name;
    double cash;
    Stock stock;
    Integer quantity;
    Map<Stock,Integer> inventory;


    @Before
    public void setUp(){
        name = "test";
        cash = 20.0;
        inventory = new HashMap<>();
        stock= new StubStock();
        quantity = 50;
        inventory.put(stock,quantity);
        trader = new DefaultTraderBuilder().cash(cash).name(name).inventory(inventory).createTrader();

    }
    @Test
    public void testName(){
        assertEquals(name,trader.getName());
    }

    @Test
    public void testCash(){
        // I know the object casts are redundant but assertEquals was being weird
        assertEquals((Object)cash,(Object)trader.getCash());
    }

    @Test
    public void testInventory(){
        assertEquals(quantity,trader.getInventoryHolding(stock));
    }
}
