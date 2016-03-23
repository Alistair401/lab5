package uk.ac.glasgow.jagora;

import java.util.List;

public interface Observer {
	public List<TickEvent<Trade>> notify(List<TickEvent<Trade>> trades);
}
