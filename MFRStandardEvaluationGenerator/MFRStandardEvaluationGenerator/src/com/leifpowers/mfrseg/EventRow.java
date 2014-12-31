package com.leifpowers.mfrseg;

import com.leifpowers.mfrseg.EventEnums.*;

public class EventRow {
	
	// True Event Enums
	public final SexOccurred sexOccurred;
	public final AccusedUsedPhysicalForce accusedUsedPhysicalForce;
	public final VictimUsedPhysicalForce victimUsedPhysicalForce;
	public final AccusedInitiatedUseOfForce accusedInitiatedUseOfForce;
	public final AccusedShoutsNo accusedShoutsNo;
	public final VictimShoutsNo victimShoutsNo;
	public final AccusedIndicatesConsent accusedIndicatesConsent;
	public final VictimIndicatesConsent victimIndicatesConsent;
	
	// Measured Event Enums
	public final FluidsPresent fluidsPresent;
	public final HairPresent hairPresent;
	public final LaterPregnancyWithDNAEvidence laterPregnancyWithDNAEvidence;
	public final EyewitnessOrVideoDocumentationOfHardNo eyewitnessOrVideoDocumentationOfHardNo;
	public final AccusedStruggleInjuries accusedStruggleInjuries;
	public final VictimStruggleInjuries victimStruggleInjuries;
	public final AccusedIntoxicationState accusedIntoxicationState;
	public final VictimIntoxicationState victimIntoxicationState;
	public final AccusedPreConsentFormAvailable accusedPreConsentFormAvailable;
	public final VictimPreConsentFormAvailable victimPreConsentFormAvailable;
	public final AccusedPriorInformalRefusal accusedPriorInformalRefusal;
	public final VictimPriorInformalRefusal victimPriorInformalRefusal;
	public final AccusedPriorSexualHistory accusedPriorSexualHistory;
	public final VictimPriorSexualHistory victimPriorSexualHistory;
	public final AccusedPriorBreachesOfDeclaration accusedPriorBreachesOfDeclaration;
	public final VictimPriorBreachesOfDeclaration victimPriorBreachesOfDeclaration;
	public final AccusedPriorRapeAllegationsAgainst accusedPriorRapeAllegationsAgainst;
	public final VictimPriorRapeAllegationsAgainst victimPriorRapeAllegationsAgainst;
	
	public EventRow(SexOccurred sexOccurred, AccusedUsedPhysicalForce accusedUsedPhysicalForce, VictimUsedPhysicalForce victimUsedPhysicalForce, AccusedInitiatedUseOfForce accusedInitiatedUseOfForce, AccusedShoutsNo accusedShoutsNo, VictimShoutsNo victimShoutsNo, AccusedIndicatesConsent accusedIndicatesConsent, VictimIndicatesConsent victimIndicatesConsent, FluidsPresent fluidsPresent, HairPresent hairPresent, LaterPregnancyWithDNAEvidence laterPregnancyWithDNAEvidence, EyewitnessOrVideoDocumentationOfHardNo eyewitnessOrVideoDocumentationOfHardNo, AccusedStruggleInjuries accusedStruggleInjuries, VictimStruggleInjuries victimStruggleInjuries, AccusedIntoxicationState accusedIntoxicationState, VictimIntoxicationState victimIntoxicationState, AccusedPreConsentFormAvailable accusedPreConsentFormAvailable, VictimPreConsentFormAvailable victimPreConsentFormAvailable, AccusedPriorInformalRefusal accusedPriorInformalRefusal, VictimPriorInformalRefusal victimPriorInformalRefusal, AccusedPriorSexualHistory accusedPriorSexualHistory, VictimPriorSexualHistory victimPriorSexualHistory, AccusedPriorBreachesOfDeclaration accusedPriorBreachesOfDeclaration, VictimPriorBreachesOfDeclaration victimPriorBreachesOfDeclaration, AccusedPriorRapeAllegationsAgainst accusedPriorRapeAllegationsAgainst, VictimPriorRapeAllegationsAgainst victimPriorRapeAllegationsAgainst) {
		this.sexOccurred = sexOccurred;
		this.accusedUsedPhysicalForce = accusedUsedPhysicalForce;
		this.victimUsedPhysicalForce = victimUsedPhysicalForce;
		this.accusedInitiatedUseOfForce = accusedInitiatedUseOfForce;
		this.accusedShoutsNo = accusedShoutsNo;
		this.victimShoutsNo = victimShoutsNo;
		this.accusedIndicatesConsent = accusedIndicatesConsent;
		this.victimIndicatesConsent = victimIndicatesConsent;
		this.fluidsPresent = fluidsPresent;
		this.hairPresent = hairPresent;
		this.laterPregnancyWithDNAEvidence = laterPregnancyWithDNAEvidence;
		this.eyewitnessOrVideoDocumentationOfHardNo = eyewitnessOrVideoDocumentationOfHardNo;
		this.accusedStruggleInjuries = accusedStruggleInjuries;
		this.victimStruggleInjuries = victimStruggleInjuries;
		this.accusedIntoxicationState = accusedIntoxicationState;
		this.victimIntoxicationState = victimIntoxicationState;
		this.accusedPreConsentFormAvailable = accusedPreConsentFormAvailable;
		this.victimPreConsentFormAvailable = victimPreConsentFormAvailable;
		this.accusedPriorInformalRefusal = accusedPriorInformalRefusal;
		this.victimPriorInformalRefusal = victimPriorInformalRefusal;
		this.accusedPriorSexualHistory = accusedPriorSexualHistory;
		this.victimPriorSexualHistory = victimPriorSexualHistory;
		this.accusedPriorBreachesOfDeclaration = accusedPriorBreachesOfDeclaration;
		this.victimPriorBreachesOfDeclaration = victimPriorBreachesOfDeclaration;
		this.accusedPriorRapeAllegationsAgainst = accusedPriorRapeAllegationsAgainst;
		this.victimPriorRapeAllegationsAgainst = victimPriorRapeAllegationsAgainst;
	}
}
