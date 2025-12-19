package models;

import java.time.LocalDate;

public class Referral {
    private String referralId;
    private String patientId;
    private String referringClinicianId;
    private String referredClinicianId;
    private String referringFacilityId;
    private String referredFacilityId;
    private LocalDate referralDate;
    private String urgencyLevel;
    private String referralReason;
    private String clinicalSummary;
    private String requestedInvestigation;
    private String status;
    private String linkedAppointmentId;
    private String notes;
    private LocalDate createdDate;
    private LocalDate lastUpdated;

    public Referral() { }

    public Referral(String referralId,
                    String patientId,
                    String referringClinicianId,
                    String referredClinicianId,
                    String referringFacilityId,
                    String referredFacilityId,
                    LocalDate referralDate,
                    String urgencyLevel,
                    String referralReason,
                    String clinicalSummary,
                    String requestedInvestigation,
                    String status,
                    String appoinmentId,
                    String notes,
                    LocalDate createdDate,
                    LocalDate lastUpdated) {
        this.referralId = referralId;
        this.patientId = patientId;
        this.referringClinicianId = referringClinicianId;
        this.referredClinicianId = referredClinicianId;
        this.referringFacilityId = referringFacilityId;
        this.referredFacilityId = referredFacilityId;
        this.referralDate = referralDate;
        this.urgencyLevel = urgencyLevel;
        this.referralReason = referralReason;
        this.clinicalSummary = clinicalSummary;
        this.requestedInvestigation = requestedInvestigation;
        this.status = status;
        this.linkedAppointmentId = linkedAppointmentId;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastUpdated = lastUpdated;
    }

    public String getReferralId() { return referralId; }
    public void setReferralId(String referralId) { this.referralId = referralId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getReferringClinicianId() { return referringClinicianId; }
    public void setReferringClinicianId(String referringClinicianId) { this.referringClinicianId = referringClinicianId; }
    public String getReferredClinicianId() { return referredClinicianId; }
    public void setReferredClinicianId(String referredClinicianId) { this.referredClinicianId = referredClinicianId; }

    public String getReferringFacilityId() { return referringFacilityId; }
    public void setReferringFacilityId(String referringFacilityId) { this.referringFacilityId = referringFacilityId; }
    public String getReferredFacilityId() { return referredFacilityId; }
    public void setReferredFacilityId(String referredFacilityId) { this.referredFacilityId = referredFacilityId; }

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

    public String getLinkedAppointmentId() { return linkedAppointmentId; }
    public void setLinkedAppointmentId(String linkedAppointmentId) { this.linkedAppointmentId = linkedAppointmentId; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public LocalDate getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDate lastUpdated) { this.lastUpdated = lastUpdated; }
}
