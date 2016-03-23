package uk.ac.glasgow.jagora;

import java.util.List;

public interface Subject {
	public void register(Observer o, Stock s);
	public void unregister(Observer o, Stock s);
	public void notifyObserver(Market market,List<TickEvent<Trade>> log);
}
