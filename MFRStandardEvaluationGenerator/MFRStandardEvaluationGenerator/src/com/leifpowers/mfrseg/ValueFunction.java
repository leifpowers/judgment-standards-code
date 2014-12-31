package com.leifpowers.mfrseg;

import java.util.ArrayList;

public abstract class ValueFunction {
	public String getName() {
		return this.getClass().getSimpleName();
	}
	
	// where 1 is ideally good, zero is ideally bad
	// feel free to print out more statistics here, I just had it return one value
	public abstract double getValue(ArrayList<EventRow> eventsMatrix, boolean[] oracle, boolean[] standard, double[] probability);
}
