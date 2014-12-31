package com.leifpowers.mfrseg;

import java.util.List;

public abstract class OracleFunction {
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public abstract boolean[] isGuilty(List<EventRow> eventsMatrix);
}
