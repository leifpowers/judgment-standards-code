package com.leifpowers.mfrseg;

import java.util.List;

import com.leifpowers.mfrseg.Main.CachedBooleanColumn;
import com.leifpowers.mfrseg.OracleFunctions.*;

public class ProbabilityEstimators {

	public static class EqualProbabilityEstimator extends ProbabilityEstimator {
		public double[] getProbabilities(List<EventRow> eventsMatrix) {
			double[] column = new double[eventsMatrix.size()];
			double evenProb = 1.0 / eventsMatrix.size();
			//System.out.println("Even probability:" + evenProb);
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				column[i] = evenProb;
			}
			return column;
		}
	}
	
	// Rough assumptions:
	// About 100,000 female sexual rape victims a year, in a population of 150 million women
	// Assume the offender is only reported once for all incidents in the year
	// (Probably) overestimate and assume each victim is raped at least twice by two different men
	// Assume people of age have sex 50 times a year (probably more like 90)
	// Assume that a third of people in the US do not have sex. (So 200 million people having sex,
	// 100 million women having sex)
	// This comes out to 200,000 rapes compared with 5 billion normal sex acts,
	// or .00004 (.004 percent).
	public static class RoughCountUSYearlyRapesComparedToTotalSexEncounters extends ProbabilityEstimator {
		public double[] getProbabilities(List<EventRow> eventsMatrix) {
			final double RAPE_PROB = .00004;
			double[] column = new double[eventsMatrix.size()];
			
			//make adjustments
			//creates dependency on oracles (FIXME)
			//using most restrictive oracle
			boolean[] mostRestrictiveOracle = null;
			for (CachedBooleanColumn oracle : Main.cachedOracles) {
				if (oracle.getName().equals(new KnowingTrueEventsAffirmativeAlcoholInvalidationOracle().getName())) {
					mostRestrictiveOracle = oracle.getColumn();
				}
			}
			
			// get correct number of rape rows to get correct percentage weighting
			// not very good performance
			int totalNumberOfRapeRows = 0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					++totalNumberOfRapeRows;
				}
			}
			
			// calculate percentages
			double probForEachRapeRow = RAPE_PROB / totalNumberOfRapeRows;
			double probForEachNonRapeRow = (1 - RAPE_PROB) / (eventsMatrix.size() - totalNumberOfRapeRows);
			
		    //diagnostics
			System.out.println("(RCUYRCTTSEVal) Number of Rape Rows: " + totalNumberOfRapeRows);
			System.out.println("(RCUYRCTTSEVal) Prob for Each Rape Row " + probForEachRapeRow);
			System.out.println("(RCUYRCTTSEVal) Prob for Each Non-Rape Row " + probForEachNonRapeRow);
			
			// now fill out for each row
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					column[i] = probForEachRapeRow;
				}
				else {
					column[i] = probForEachNonRapeRow;
				}
			}
			
			//sanity check
			double totalProbability = 0.0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				totalProbability += column[i];
			}
			System.out.println(this.getName() + " total probability: " + totalProbability);
			
			return column;
		}
	}
	
	// This is on a different basis than the others. It is an attempt to express the probability
	// that, for all of the rape reports given, that 90% of them are accurate.
	// In terms of the distribution, this basically means that the non-rape ones
	// get weighted 10%, and the rape ones get weighted 90% in total. 
	public static class ReportingBased90PercentRapeAAI extends ProbabilityEstimator {
		public double[] getProbabilities(List<EventRow> eventsMatrix) {
			final double RAPE_PROB = .9;
			double[] column = new double[eventsMatrix.size()];
			
			//make adjustments
			//creates dependency on oracles (FIXME)
			//using most restrictive oracle
			boolean[] mostRestrictiveOracle = null;
			for (CachedBooleanColumn oracle : Main.cachedOracles) {
				if (oracle.getName().equals(new KnowingTrueEventsAffirmativeAlcoholInvalidationOracle().getName())) {
					mostRestrictiveOracle = oracle.getColumn();
				}
			}
			
			// get correct number of rape rows to get correct percentage weighting
			// not very good performance
			int totalNumberOfRapeRows = 0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					++totalNumberOfRapeRows;
				}
			}
			
			// calculate percentages
			double probForEachRapeRow = RAPE_PROB / totalNumberOfRapeRows;
			double probForEachNonRapeRow = (1 - RAPE_PROB) / (eventsMatrix.size() - totalNumberOfRapeRows);
			
		    //diagnostics
			System.out.println(this.getName() + " Number of Rape Rows: " + totalNumberOfRapeRows);
			System.out.println(this.getName() + " Prob for Each Rape Row " + probForEachRapeRow);
			System.out.println(this.getName() + " Prob for Each Non-Rape Row " + probForEachNonRapeRow);
			
			// now fill out for each row
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					column[i] = probForEachRapeRow;
				}
				else {
					column[i] = probForEachNonRapeRow;
				}
			}
			
			//sanity check
			double totalProbability = 0.0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				totalProbability += column[i];
			}
			System.out.println(this.getName() + " total probability: " + totalProbability);
			
			return column;
		}
	}
	
	// This is on a different basis than the others. It is an attempt to express the probability
	// that, for all of the rape reports given, that 90% of them are accurate.
	// In terms of the distribution, this basically means that the non-rape ones
	// get weighted 10%, and the rape ones get weighted 90% in total. 
	public static class ReportingBased90PercentRapeA extends ProbabilityEstimator {
		public double[] getProbabilities(List<EventRow> eventsMatrix) {
			final double RAPE_PROB = .9;
			double[] column = new double[eventsMatrix.size()];
			
			//make adjustments
			//creates dependency on oracles (FIXME)
			//using most restrictive oracle
			boolean[] mostRestrictiveOracle = null;
			for (CachedBooleanColumn oracle : Main.cachedOracles) {
				if (oracle.getName().equals(new KnowingTrueEventsAffirmativeOracle().getName())) {
					mostRestrictiveOracle = oracle.getColumn();
				}
			}
			
			// get correct number of rape rows to get correct percentage weighting
			// not very good performance
			int totalNumberOfRapeRows = 0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					++totalNumberOfRapeRows;
				}
			}
			
			// calculate percentages
			double probForEachRapeRow = RAPE_PROB / totalNumberOfRapeRows;
			double probForEachNonRapeRow = (1 - RAPE_PROB) / (eventsMatrix.size() - totalNumberOfRapeRows);
			
		    //diagnostics
			System.out.println(this.getName() + " Number of Rape Rows: " + totalNumberOfRapeRows);
			System.out.println(this.getName() + " Prob for Each Rape Row " + probForEachRapeRow);
			System.out.println(this.getName() + " Prob for Each Non-Rape Row " + probForEachNonRapeRow);
			
			// now fill out for each row
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					column[i] = probForEachRapeRow;
				}
				else {
					column[i] = probForEachNonRapeRow;
				}
			}
			
			//sanity check
			double totalProbability = 0.0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				totalProbability += column[i];
			}
			System.out.println(this.getName() + " total probability: " + totalProbability);
			
			return column;
		}
	}
	
	// This is on a different basis than the others. It is an attempt to express the probability
	// that, for all of the rape reports given, that 90% of them are accurate.
	// In terms of the distribution, this basically means that the non-rape ones
	// get weighted 10%, and the rape ones get weighted 90% in total. 
	public static class ReportingBased90PercentRapeNR extends ProbabilityEstimator {
		public double[] getProbabilities(List<EventRow> eventsMatrix) {
			final double RAPE_PROB = .9;
			double[] column = new double[eventsMatrix.size()];
			
			//make adjustments
			//creates dependency on oracles (FIXME)
			//using most restrictive oracle
			boolean[] mostRestrictiveOracle = null;
			for (CachedBooleanColumn oracle : Main.cachedOracles) {
				if (oracle.getName().equals(new KnowingTrueEventsNonRefusalOracle().getName())) {
					mostRestrictiveOracle = oracle.getColumn();
				}
			}
			
			// get correct number of rape rows to get correct percentage weighting
			// not very good performance
			int totalNumberOfRapeRows = 0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					++totalNumberOfRapeRows;
				}
			}
			
			// calculate percentages
			double probForEachRapeRow = RAPE_PROB / totalNumberOfRapeRows;
			double probForEachNonRapeRow = (1 - RAPE_PROB) / (eventsMatrix.size() - totalNumberOfRapeRows);
			
		    //diagnostics
			System.out.println(this.getName() + " Number of Rape Rows: " + totalNumberOfRapeRows);
			System.out.println(this.getName() + " Prob for Each Rape Row " + probForEachRapeRow);
			System.out.println(this.getName() + " Prob for Each Non-Rape Row " + probForEachNonRapeRow);
			
			// now fill out for each row
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				if (mostRestrictiveOracle[i] == true) {
					column[i] = probForEachRapeRow;
				}
				else {
					column[i] = probForEachNonRapeRow;
				}
			}
			
			//sanity check
			double totalProbability = 0.0;
			for (int i = 0; i < eventsMatrix.size(); ++i) {
				totalProbability += column[i];
			}
			System.out.println(this.getName() + " total probability: " + totalProbability);
			
			return column;
		}
	}
}
