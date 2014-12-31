package com.leifpowers.mfrseg;

import java.util.List;

import com.leifpowers.mfrseg.EventEnums.*;

/*
 * Only programmer vigilance prevents the use of true event enums. 
 */
public class JudgmentStandards {
	public static class ProofBeyondReasonableDoubtActOnly extends JudgmentStandard {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0; 
			
			for (EventRow e : eventsMatrix) {
				// if someone caught them red-handed, they're guilty
				if (e.eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES)) {
					column[i] = true; 
				}
				// otherwise, if physical evidence indicates that sex happened...
				else if ((e.hairPresent.equals(HairPresent.YES) || e.fluidsPresent.equals(FluidsPresent.YES) || e.laterPregnancyWithDNAEvidence.equals(LaterPregnancyWithDNAEvidence.YES))) {
					//and the victim was seriously injured while the attacker was not
					if (e.accusedStruggleInjuries.equals(AccusedStruggleInjuries.NO) && e.victimStruggleInjuries.equals(VictimStruggleInjuries.YES)) {
						column[i] = true;
					}
					else {
						column[i] = false;
					}
				}
				else {
					column[i] = false;
				}
				
				++i;
			}
			
			return column;
		}
	}
	
	public static class PreponderanceOfTheEvidenceBoxCount extends JudgmentStandard {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;
			
			for (EventRow e : eventsMatrix) {
				// if someone caught them red-handed, they're guilty
				if (e.eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES)) {
					column[i] = true; 
				}
				// otherwise, if physical evidence indicates that sex happened...
				else if ((e.hairPresent.equals(HairPresent.YES) || e.fluidsPresent.equals(FluidsPresent.YES) || e.laterPregnancyWithDNAEvidence.equals(LaterPregnancyWithDNAEvidence.YES))) {
					//for every measured fact of intent, count +1 if it would indicate nonconsent
					//count -1 for any fact which would indicate the reverse
					//count zero for anything else
					int netCount = 0;
					
					if (e.accusedStruggleInjuries.equals(AccusedStruggleInjuries.YES)) {
						netCount -= 1;
					}
					if (e.victimStruggleInjuries.equals(VictimStruggleInjuries.YES)) {
						netCount += 1;
					}
					if (e.accusedIntoxicationState.equals(AccusedIntoxicationState.YES)) {
						netCount -= 1;
					}
					if (e.victimIntoxicationState.equals(VictimIntoxicationState.YES)) {
						netCount += 1;
					}
					if (e.accusedPreConsentFormAvailable.equals(AccusedPreConsentFormAvailable.YES)) {
						netCount -= 1;
					}
					if (e.victimPreConsentFormAvailable.equals(VictimPreConsentFormAvailable.YES)) {
						netCount += 1;
					}
					if (e.accusedPriorInformalRefusal.equals(AccusedPriorInformalRefusal.YES)) {
						netCount -= 1;
					}
					if (e.victimPriorInformalRefusal.equals(VictimPriorInformalRefusal.YES)) {
						netCount += 1;
					}
					if (e.accusedPriorSexualHistory.equals(AccusedPriorSexualHistory.YES)) {
						netCount += 1;
					}
					if (e.victimPriorSexualHistory.equals(VictimPriorSexualHistory.YES)) {
						netCount -= 1;
					}
					if (e.accusedPriorBreachesOfDeclaration.equals(AccusedPriorBreachesOfDeclaration.YES)) {
						netCount += 1;
					}
					if (e.victimPriorBreachesOfDeclaration.equals(VictimPriorBreachesOfDeclaration.YES)) {
						netCount -= 1;
					}
					if (e.accusedPriorRapeAllegationsAgainst.equals(AccusedPriorRapeAllegationsAgainst.YES)) {
						netCount += 1;
					}
					if (e.victimPriorRapeAllegationsAgainst.equals(VictimPriorRapeAllegationsAgainst.YES)) {
						netCount -= 1;
					}
					
					if (netCount > 0) {
						column[i] = true;
					}
					else {
						column[i] = false;
					}
				}
				
				++i;
			}
			
			return column;
		}
	}
	
	public static class PreConsentRequiredGreaterForceGuiltyHairSufficient extends JudgmentStandard {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;
			
			for (EventRow e : eventsMatrix) {
				// if someone caught them red-handed, they're guilty
				if (e.eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES)) {
					column[i] = true; 
				}
				// otherwise, if physical evidence indicates that sex happened...
				else if ((e.hairPresent.equals(HairPresent.YES) || e.fluidsPresent.equals(FluidsPresent.YES) || e.laterPregnancyWithDNAEvidence.equals(LaterPregnancyWithDNAEvidence.YES))) {
					// if the victim previously refused, then guilty
					if (e.victimPreConsentFormAvailable.equals(VictimPreConsentFormAvailable.YES) || e.victimPriorInformalRefusal.equals(VictimPriorInformalRefusal.YES)) {
						column[i] = true;
					}
					//or if the victim was seriously injured while the attacker was not
				    else if (e.accusedStruggleInjuries.equals(AccusedStruggleInjuries.NO) && e.victimStruggleInjuries.equals(VictimStruggleInjuries.YES)) {
						column[i] = true;
					}
					else {
						column[i] = false;
					}
				}
				else {
					column[i] = false;
				}
				
				++i;
			}
			
			return column;
		}
	}
	
	public static class PreConsentRequiredGreaterForceGuiltyHairInsufficient extends JudgmentStandard {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;
			
			for (EventRow e : eventsMatrix) {
				// if someone caught them red-handed, they're guilty
				if (e.eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES)) {
					column[i] = true; 
				}
				// otherwise, if physical evidence indicates that sex happened...
				else if ((e.fluidsPresent.equals(FluidsPresent.YES) || e.laterPregnancyWithDNAEvidence.equals(LaterPregnancyWithDNAEvidence.YES))) {
					// if the victim previously refused, then guilty
					if (e.victimPreConsentFormAvailable.equals(VictimPreConsentFormAvailable.YES) || e.victimPriorInformalRefusal.equals(VictimPriorInformalRefusal.YES)) {
						column[i] = true;
					}
					//or if the victim was seriously injured while the attacker was not
				    else if (e.accusedStruggleInjuries.equals(AccusedStruggleInjuries.NO) && e.victimStruggleInjuries.equals(VictimStruggleInjuries.YES)) {
						column[i] = true;
					}
					else {
						column[i] = false;
					}
				}
				else {
					column[i] = false;
				}
				
				++i;
			}
			
			return column;
		}
	}
	
	
	public static class PreConsentRequiredGreaterForceHairSufficientAlcoholInvalidation extends JudgmentStandard {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;
			
			for (EventRow e : eventsMatrix) {
				// if someone caught them red-handed, they're guilty
				if (e.eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES)) {
					column[i] = true; 
				}
				// otherwise, if physical evidence indicates that sex happened...
				else if ((e.hairPresent.equals(HairPresent.YES) || e.fluidsPresent.equals(FluidsPresent.YES) || e.laterPregnancyWithDNAEvidence.equals(LaterPregnancyWithDNAEvidence.YES))) {
					// if the victim previously refused, then guilty
					if (e.victimPreConsentFormAvailable.equals(VictimPreConsentFormAvailable.YES) || e.victimPriorInformalRefusal.equals(VictimPriorInformalRefusal.YES)) {
						column[i] = true;
					}
					//or if the victim was seriously injured while the attacker was not
				    else if (e.accusedStruggleInjuries.equals(AccusedStruggleInjuries.NO) && e.victimStruggleInjuries.equals(VictimStruggleInjuries.YES)) {
						column[i] = true;
					}
					//or if we assume that the victim cannot consent if intoxicated
				    else if (e.victimIntoxicationState.equals(VictimIntoxicationState.YES)) {
				    	column[i] = true;
				    }
					else {
						column[i] = false;
					}
				}
				else {
					column[i] = false;
				}
				
				++i;
			}
			
			return column;
		}
	}
	
	public static class PreConsentFormOrViolenceRequired extends JudgmentStandard {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;
			
			for (EventRow e : eventsMatrix) {
				// if someone caught them red-handed, they're guilty
				if (e.eyewitnessOrVideoDocumentationOfHardNo.equals(EyewitnessOrVideoDocumentationOfHardNo.YES)) {
					column[i] = true; 
				}
				// otherwise, if physical evidence indicates that sex happened...
				else if ((e.hairPresent.equals(HairPresent.YES) || e.fluidsPresent.equals(FluidsPresent.YES) || e.laterPregnancyWithDNAEvidence.equals(LaterPregnancyWithDNAEvidence.YES))) {
					// if the victim has a consent form on file and we can actually trust it
					if (e.victimPreConsentFormAvailable.equals(VictimPreConsentFormAvailable.YES) && e.victimPriorBreachesOfDeclaration.equals(VictimPriorBreachesOfDeclaration.NO)) {
						column[i] = true;
					}
					//or if the victim was seriously injured while the attacker was not
				    else if (e.accusedStruggleInjuries.equals(AccusedStruggleInjuries.NO) && e.victimStruggleInjuries.equals(VictimStruggleInjuries.YES)) {
						column[i] = true;
					}
					else {
						column[i] = false;
					}
				}
				else {
					column[i] = false;
				}
				
				++i;
			}
			
			return column;
		}
	}
}
