package com.leifpowers.mfrseg;

import java.util.List;

public abstract class JudgmentStandard {
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public abstract boolean[] isGuilty(List<EventRow> eventsMatrix);
}
