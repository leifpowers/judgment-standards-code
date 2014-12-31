package com.leifpowers.mfrseg;

import java.util.ArrayList;

import com.leifpowers.mfrseg.JudgmentStandards.*;
import com.leifpowers.mfrseg.OracleFunctions.*;
import com.leifpowers.mfrseg.ProbabilityEstimators.*;
import com.leifpowers.mfrseg.ValueFunctions.*;
import com.leifpowers.mfrseg.EventEnums.*;

public class Main {

	//made class-level to permit additional evaluations beyond scope of main
	public static ArrayList<EventRow> eventsMatrix;
	
	public static class CachedBooleanColumn {
		public String name;
		public boolean[] column;
		
		public CachedBooleanColumn(String name, boolean[] column) {
			this.name = name;
			this.column = column;
		}
		
		public String getName() {
			return name;
		}
		
		// kind of ugly since it returns the reference
		public boolean[] getColumn() {
			return column;
		}
	}
	
	public static class CachedDoubleColumn {
		public String name;
		public double[] column;
		
		public CachedDoubleColumn(String name, double[] column) {
			this.name = name;
			this.column = column;
		}
		
		public String getName() {
			return name;
		}
		
		// kind of ugly since it returns the reference
		public double[] getColumn() {
			return column;
		}
	}
	
	public static ArrayList<CachedBooleanColumn> cachedOracles = new ArrayList<CachedBooleanColumn>();
	public static ArrayList<CachedBooleanColumn> cachedStandards = new ArrayList<CachedBooleanColumn>();
	public static ArrayList<CachedDoubleColumn> cachedProbabilities = new ArrayList<CachedDoubleColumn>();
	
	public static ArrayList<ValueFunction> valuations = new ArrayList<ValueFunction>();
	
	/**
	 * Not all combinations of the sub-factors make sense. For example, it does not quite make sense to have
	 * fluids present if no sex actually occurred. Nor does it make sense to have the victim consent and shout no. 
	 * @return the actual realistic and consistent list of events, assuming that nobody beat themselves up,
	 * cleaned up the victim, forged evidence, etc. The simplifying assumption is that if there is strong evidence that 
	 * there was evidence tampering by either party, their credibility has been so thoroughly destroyed that there
	 * isn't any point in trying to sort out what they wound up altering: you either throw them in prison or
	 * dismiss the case. 
	 */
	public static ArrayList<EventRow> generateRealisticNonEvidenceTamperedEventsMatrix() {
		ArrayList<EventRow> eventsMatrix = new ArrayList<EventRow>(2000000);
		
		for (SexOccurred sexOccurred : SexOccurred.values()) {
			for (AccusedUsedPhysicalForce accusedUsedPhysicalForce : AccusedUsedPhysicalForce.values()) {
				for (VictimUsedPhysicalForce victimUsedPhysicalForce : VictimUsedPhysicalForce.values()) {
					for (AccusedInitiatedUseOfForce accusedInitiatedUseOfForce : AccusedInitiatedUseOfForce.values()) {
						for (AccusedShoutsNo accusedShoutsNo : AccusedShoutsNo.values()) {
							for (VictimShoutsNo victimShoutsNo : VictimShoutsNo.values()) {

								for (AccusedIndicatesConsent accusedIndicatesConsent : AccusedIndicatesConsent.values()) {									
									if (accusedShoutsNo.equals(AccusedShoutsNo.NO)) {

										for (VictimIndicatesConsent victimIndicatesConsent : VictimIndicatesConsent.values()) {											
											if(victimShoutsNo.equals(VictimShoutsNo.NO)) {

												for (FluidsPresent fluidsPresent : FluidsPresent.values()) {
													if (!(sexOccurred.equals(SexOccurred.NO) && fluidsPresent.equals(FluidsPresent.YES))) {

														for (HairPresent hairPresent : HairPresent.values()) {
															if (!(sexOccurred.equals(SexOccurred.NO) && hairPresent.equals(HairPresent.YES))) {
																
																// pregnancy could have occurred as a result of prior sex acts
																// maybe this needs to get cleaned up
																for (LaterPregnancyWithDNAEvidence laterPregnancyWithDNAEvidence : LaterPregnancyWithDNAEvidence.values()) {

																	for (EyewitnessOrVideoDocumentationOfHardNo eyewitnessOrVideoDocumentationOfHardNo : EyewitnessOrVideoDocumentationOfHardNo.values()) {
																		if (!(victimShoutsNo.equals(VictimShoutsNo.NO) && eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES))) {

																			for (AccusedStruggleInjuries accusedStruggleInjuries : AccusedStruggleInjuries.values()) {
																				if (!(accusedUsedPhysicalForce.equals(AccusedUsedPhysicalForce.NO) && victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.NO))) {

																					for (VictimStruggleInjuries victimStruggleInjuries : VictimStruggleInjuries.values()) {
																						// could be optimized out, leaving in here to have correct constraint
																						if (!(accusedUsedPhysicalForce.equals(AccusedUsedPhysicalForce.NO) && victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.NO))) {

																							for (AccusedIntoxicationState accusedIntoxicationState : AccusedIntoxicationState.values()) {
																								for (VictimIntoxicationState victimIntoxicationState : VictimIntoxicationState.values()) {
																									for (AccusedPreConsentFormAvailable accusedPreConsentFormAvailable : AccusedPreConsentFormAvailable.values()) {
																										for (VictimPreConsentFormAvailable victimPreConsentFormAvailable : VictimPreConsentFormAvailable.values()) {
																											for (AccusedPriorInformalRefusal accusedPriorInformalRefusal : AccusedPriorInformalRefusal.values()) {
																												for (VictimPriorInformalRefusal victimPriorInformalRefusal : VictimPriorInformalRefusal.values()) {
																													for (AccusedPriorSexualHistory accusedPriorSexualHistory : AccusedPriorSexualHistory.values()) {
																														for (VictimPriorSexualHistory victimPriorSexualHistory : VictimPriorSexualHistory.values()) {
																															for (AccusedPriorBreachesOfDeclaration accusedPriorBreachesOfDeclaration : AccusedPriorBreachesOfDeclaration.values()) {
																																for (VictimPriorBreachesOfDeclaration victimPriorBreachesOfDeclaration : VictimPriorBreachesOfDeclaration.values()) {
																																	for (AccusedPriorRapeAllegationsAgainst accusedPriorRapeAllegationsAgainst : AccusedPriorRapeAllegationsAgainst.values()) {
																																		for (VictimPriorRapeAllegationsAgainst victimPriorRapeAllegationsAgainst : VictimPriorRapeAllegationsAgainst.values()) {
																																			EventRow eventRow = new EventRow(sexOccurred, accusedUsedPhysicalForce, victimUsedPhysicalForce, accusedInitiatedUseOfForce, accusedShoutsNo, victimShoutsNo, accusedIndicatesConsent, victimIndicatesConsent, fluidsPresent, hairPresent, laterPregnancyWithDNAEvidence, eyewitnessOrVideoDocumentationOfHardNo, accusedStruggleInjuries, victimStruggleInjuries, accusedIntoxicationState, victimIntoxicationState, accusedPreConsentFormAvailable, victimPreConsentFormAvailable, accusedPriorInformalRefusal, victimPriorInformalRefusal, accusedPriorSexualHistory, victimPriorSexualHistory, accusedPriorBreachesOfDeclaration, victimPriorBreachesOfDeclaration, accusedPriorRapeAllegationsAgainst, victimPriorRapeAllegationsAgainst);																																		
																																			eventsMatrix.add(eventRow);
																																		}	
																																	}	
																																}	
																															}	
																														}	
																													}	
																												}
																											}
																										}
																									}
																								}
																							}
																						}
																					}
																				}
																			}
																		}
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		return eventsMatrix;
	}

	public static void printResults() {
		for (ValueFunction valueFunction : valuations) {
			for (CachedBooleanColumn oracle : cachedOracles) {
				for (CachedBooleanColumn standard : cachedStandards) {
					for (CachedDoubleColumn probability: cachedProbabilities) {
						System.out.println(valueFunction.getName() + "|" + oracle.getName() + "|\n" 
								+ standard.getName() + "|" + probability.getName() + ":");
						System.out.println(valueFunction.getValue(eventsMatrix, oracle.getColumn(), standard.getColumn(), probability.getColumn()));
					}
				}
			}
		}
	}
	
	public static void generateResults() {
		// first, generate the matrix of actual true events, plus the measured events associated with them
		// this will be in the millions of rows
		eventsMatrix = generateRealisticNonEvidenceTamperedEventsMatrix();
		System.out.println("Size of eventsMatrix:" + eventsMatrix.size());
		
		cachedOracles.add(new CachedBooleanColumn(new KnowingTrueEventsAffirmativeAlcoholInvalidationOracle().getName(), new KnowingTrueEventsAffirmativeAlcoholInvalidationOracle().isGuilty(eventsMatrix)));
		cachedOracles.add(new CachedBooleanColumn(new KnowingTrueEventsAffirmativeOracle().getName(), new KnowingTrueEventsAffirmativeOracle().isGuilty(eventsMatrix)));
		cachedOracles.add(new CachedBooleanColumn(new KnowingTrueEventsNonRefusalOracle().getName(), new KnowingTrueEventsNonRefusalOracle().isGuilty(eventsMatrix)));
		
		cachedStandards.add(new CachedBooleanColumn(new ProofBeyondReasonableDoubtActOnly().getName(), new ProofBeyondReasonableDoubtActOnly().isGuilty(eventsMatrix)));
		cachedStandards.add(new CachedBooleanColumn(new PreponderanceOfTheEvidenceBoxCount().getName(), new PreponderanceOfTheEvidenceBoxCount().isGuilty(eventsMatrix)));
		cachedStandards.add(new CachedBooleanColumn(new PreConsentRequiredGreaterForceGuiltyHairSufficient().getName(), new PreConsentRequiredGreaterForceGuiltyHairSufficient().isGuilty(eventsMatrix)));
		//commented out for performance/verbosity reasons
		//cachedStandards.add(new CachedBooleanColumn(new PreConsentRequiredGreaterForceGuiltyHairInsufficient().getName(), new PreConsentRequiredGreaterForceGuiltyHairInsufficient().isGuilty(eventsMatrix)));
		cachedStandards.add(new CachedBooleanColumn(new PreConsentRequiredGreaterForceHairSufficientAlcoholInvalidation().getName(), new PreConsentRequiredGreaterForceHairSufficientAlcoholInvalidation().isGuilty(eventsMatrix)));
		cachedStandards.add(new CachedBooleanColumn(new PreConsentFormOrViolenceRequired().getName(), new PreConsentFormOrViolenceRequired().isGuilty(eventsMatrix)));
				
		cachedProbabilities.add(new CachedDoubleColumn(new EqualProbabilityEstimator().getName(), new EqualProbabilityEstimator().getProbabilities(eventsMatrix)));
		cachedProbabilities.add(new CachedDoubleColumn(new RoughCountUSYearlyRapesComparedToTotalSexEncounters().getName(), new RoughCountUSYearlyRapesComparedToTotalSexEncounters().getProbabilities(eventsMatrix)));		
		cachedProbabilities.add(new CachedDoubleColumn(new ReportingBased90PercentRapeAAI().getName(), new ReportingBased90PercentRapeAAI().getProbabilities(eventsMatrix)));		
		cachedProbabilities.add(new CachedDoubleColumn(new ReportingBased90PercentRapeA().getName(), new ReportingBased90PercentRapeA().getProbabilities(eventsMatrix)));		
		cachedProbabilities.add(new CachedDoubleColumn(new ReportingBased90PercentRapeNR().getName(), new ReportingBased90PercentRapeNR().getProbabilities(eventsMatrix)));		
				
		valuations.add(new PercentageAccurateProbabilityWeighted());
		
		printResults();
	}
	
	public static void main(String[] args) {
		System.out.println("Starting MFRStandardEvaluationGenerator");
		generateResults();
		System.out.println("Done!");
	}

}
