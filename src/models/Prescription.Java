package models;

import java.time.LocalDate;

public class Prescription {

    private String prescriptionId;
    private String patientId;
    private String clinicianId;
    private String appointmentId;   // may be null/blank
    private LocalDate prescriptionDate;

    // single medication fields from CSV
    private String medicationName;
    private String dosage;          // "20mg"
    private String frequency;       // "Once daily"
    private int durationDays;       // 28
    private String quantity;        // "28 tablets"

    private String instructions;
    private String pharmacyName;
    private String status;          // "Issued", "Collected", etc.
    private LocalDate issueDate;
    private LocalDate collectionDate; // may be null

    public Prescription() { }

    public Prescription(String prescriptionId,
                        String patientId,
                        String clinicianId,
                        String appointmentId,
                        LocalDate prescriptionDate,
                        String medicationName,
                        String dosage,
                        String frequency,
                        int durationDays,
                        String quantity,
                        String instructions,
                        String pharmacyName,
                        String status,
                        LocalDate issueDate,
                        LocalDate collectionDate) {

        this.prescriptionId = prescriptionId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.appointmentId = appointmentId;
        this.prescriptionDate = prescriptionDate;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.durationDays = durationDays;
        this.quantity = quantity;
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.status = status;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    // getters and setters

    public String getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(String prescriptionId) { this.prescriptionId = prescriptionId; }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public LocalDate getPrescriptionDate() { return prescriptionDate; }
    public void setPrescriptionDate(LocalDate prescriptionDate) { this.prescriptionDate = prescriptionDate; }

    public String getMedicationName() { return medicationName; }
    public void setMedicationName(String medicationName) { this.medicationName = medicationName; }

    public String getDosage() { return dosage; }
    public void setDosage(String dosage) { this.dosage = dosage; }

    public String getFrequency() { return frequency; }
    public void setFrequency(String frequency) { this.frequency = frequency; }

    public int getDurationDays() { return durationDays; }
    public void setDurationDays(int durationDays) { this.durationDays = durationDays; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getPharmacyName() { return pharmacyName; }
    public void setPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getCollectionDate() { return collectionDate; }
    public void setCollectionDate(LocalDate collectionDate) { this.collectionDate = collectionDate; }
}
