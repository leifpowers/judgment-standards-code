package com.leifpowers.mfrseg;

import java.util.ArrayList;

public class ValueFunctions {
	public static class PercentageAccurateProbabilityWeighted extends ValueFunction {		
		public double getValue(ArrayList<EventRow> eventsMatrix, boolean[] oracle, boolean[] standard, double[] probability) {
			double accurateDeterminationProb = 0.0;
			double falsePositiveProb = 0.0;
			double falseNegativeProb = 0.0;

			for (int i = 0; i < oracle.length; ++i) {
				if (oracle[i] == standard[i]) {
					accurateDeterminationProb += probability[i];
				}
				else if (oracle[i] == false && standard[i] == true) {
					falsePositiveProb += probability[i];
				}
				else if (oracle[i] == true && standard[i] == false) {
					falseNegativeProb += probability[i];
				}
			}
			
			System.out.println("Accurate Determination Prob: " + accurateDeterminationProb);
			System.out.println("False Positive Prob: " + falsePositiveProb);
			System.out.println("False Negative Prob: " + falseNegativeProb);

			return accurateDeterminationProb;
		}
	}
}
