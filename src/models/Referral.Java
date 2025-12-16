package models;

import java.time.LocalDate;

public class Referral {
    private String referralId;
    private Patient patient;
    private Clinician referringClinician;
    private Clinician referredClinician;
    private Facility referringFacility;
    private Facility referredFacility;
    private LocalDate referralDate;
    private String urgencyLevel;
    private String referralReason;
    private String clinicalSummary;
    private String requestedInvestigation;
    private String status;
    private Appointment linkedAppointment;
    private String notes;
    private LocalDate createdDate;
    private LocalDate lastUpdated;

    public Referral() { }

    public Referral(String referralId,
                    Patient patient,
                    Clinician referringClinician,
                    Clinician referredClinician,
                    Facility referringFacility,
                    Facility referredFacility,
                    LocalDate referralDate,
                    String urgencyLevel,
                    String referralReason,
                    String clinicalSummary,
                    String requestedInvestigation,
                    String status,
                    Appointment linkedAppointment,
                    String notes,
                    LocalDate createdDate,
                    LocalDate lastUpdated) {
        this.referralId = referralId;
        this.patient = patient;
        this.referringClinician = referringClinician;
        this.referredClinician = referredClinician;
        this.referringFacility = referringFacility;
        this.referredFacility = referredFacility;
        this.referralDate = referralDate;
        this.urgencyLevel = urgencyLevel;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.requestedInvestigation = requestedInvestigation;
        this.status = status;
        this.linkedAppointment = linkedAppointment;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    public String getReferralId() { return referralId; }
    public void setReferralId(String referralId) { this.referralId = referralId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Clinician getReferringClinician() { return referringClinician; }
    public void setReferringClinician(Clinician referringClinician) { this.referringClinician = referringClinician; }

    public Clinician getReferredClinician() { return referredClinician; }
    public void setReferredClinician(Clinician referredClinician) { this.referredClinician = referredClinician; }

    public Facility getReferringFacility() { return referringFacility; }
    public void setReferringFacility(Facility referringFacility) { this.referringFacility = referringFacility; }

    public Facility getReferredFacility() { return referredFacility; }
    public void setReferredFacility(Facility referredFacility) { this.referredFacility = referredFacility; }

    public LocalDate getReferralDate() { return referralDate; }
    public void setReferralDate(LocalDate referralDate) { this.referralDate = referralDate; }

    public String getUrgencyLevel() { return urgencyLevel; }
    public void setUrgencyLevel(String urgencyLevel) { this.urgencyLevel = urgencyLevel; }

    public String getReferralReason() { return referralReason; }
    public void setReferralReason(String referralReason) { this.referralReason = referralReason; }

    public String getClinicalSummary() { return clinicalSummary; }
    public void setClinicalSummary(String clinicalSummary) { this.clinicalSummary = clinicalSummary; }

    public String getRequestedInvestigation() { return requestedInvestigation; }
    public void setRequestedInvestigation(String requestedInvestigation) { this.requestedInvestigation = requestedInvestigation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Appointment getLinkedAppointment() { return linkedAppointment; }
    public void setLinkedAppointment(Appointment linkedAppointment) { this.linkedAppointment = linkedAppointment; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }
}
