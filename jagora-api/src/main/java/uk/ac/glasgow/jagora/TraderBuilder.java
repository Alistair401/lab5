package uk.ac.glasgow.jagora;

import java.util.Map;

public interface TraderBuilder {
	public TraderBuilder name(String newName);
	public TraderBuilder inventory(Map<Stock,Integer> inventory);
	public TraderBuilder cash(double newCash);
	public Trader createTrader();
}
