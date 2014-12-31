package com.leifpowers.mfrseg;

import java.util.List;

public abstract class ProbabilityEstimator {
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	public abstract double[] getProbabilities(List<EventRow> eventsMatrix);
}
