package com.leifpowers.mfrseg;

import java.util.List;

import com.leifpowers.mfrseg.EventEnums.*;

/*
 * Only programmer vigilance prevents the use of measured event enums.
 */
public class OracleFunctions {

	public static class KnowingTrueEventsAffirmativeAlcoholInvalidationOracle extends OracleFunction {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;

			for (EventRow e : eventsMatrix) {
				// if the attacker forced himself on her, doesn't matter what else
				if ((e.sexOccurred.equals(SexOccurred.YES) && e.accusedUsedPhysicalForce.equals(AccusedUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))
						//or if any case where she did not initiate force and had sex while she protested
						|| (e.sexOccurred.equals(SexOccurred.YES) && !((e.victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))) && e.victimShoutsNo.equals(VictimShoutsNo.YES))
						//or additionally if she never consented to the act
						|| (e.sexOccurred.equals(SexOccurred.YES) && !((e.victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))) && e.victimIndicatesConsent.equals(VictimIndicatesConsent.NO))
						//or if she was drunk
						|| (e.victimIntoxicationState.equals(VictimIntoxicationState.YES))) {
					column[i] = true;
				}
				else {
					column[i] = false;
				}

				++i;
			}

			return column;
		}
	}

	public static class KnowingTrueEventsAffirmativeOracle extends OracleFunction {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;

			for (EventRow e : eventsMatrix) {
				// if the attacker forced himself on her, doesn't matter what else
				if ((e.sexOccurred.equals(SexOccurred.YES) && e.accusedUsedPhysicalForce.equals(AccusedUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))
						//or if any case where she did not initiate force and had sex while she protested
						|| (e.sexOccurred.equals(SexOccurred.YES) && !((e.victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))) && e.victimShoutsNo.equals(VictimShoutsNo.YES))
						//or additionally if she never consented to the act
						|| (e.sexOccurred.equals(SexOccurred.YES) && !((e.victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))) && e.victimIndicatesConsent.equals(VictimIndicatesConsent.NO))) {
					column[i] = true;
				}
				else {
					column[i] = false;
				}

				++i;
			}

			return column;
		}
	}

	public static class KnowingTrueEventsNonRefusalOracle extends OracleFunction {
		public boolean[] isGuilty(List<EventRow> eventsMatrix) {
			boolean[] column = new boolean[eventsMatrix.size()];
			int i = 0;

			for (EventRow e : eventsMatrix) {
				// if the attacker forced himself on her, doesn't matter what else
				if ((e.sexOccurred.equals(SexOccurred.YES) && (e.accusedUsedPhysicalForce.equals(AccusedUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES)))
						//or in any case where she did not initiate force and had sex while she protested
						|| (e.sexOccurred.equals(SexOccurred.YES) && !((e.victimUsedPhysicalForce.equals(VictimUsedPhysicalForce.YES) && e.accusedInitiatedUseOfForce.equals(AccusedInitiatedUseOfForce.YES))) && e.victimShoutsNo.equals(VictimShoutsNo.YES))) {
					column[i] = true;
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