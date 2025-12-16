package models;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Prescription {
    private String prescriptionId;
    private Patient patient;
    private Clinician prescriber;
    private Appointment appointment;   // may be null for repeat
    private LocalDate prescriptionDate;
    private List<Medication> medications;
    private String instructions;
    private String pharmacyName;
    private LocalDate issueDate;
    private LocalDate collectionDate;

    public Prescription() {
        this.medications = new ArrayList<>();
    }

    public Prescription(String prescriptionId,
                        Patient patient,
                        Clinician prescriber,
                        Appointment appointment,
                        LocalDate prescriptionDate,
                        List<Medication> medications,
                        String instructions,
                        String pharmacyName,
                        LocalDate issueDate,
                        LocalDate collectionDate) {
        this.prescriptionId = prescriptionId;
        this.patient = patient;
        this.prescriber = prescriber;
        this.appointment = appointment;
        this.prescriptionDate = prescriptionDate;
        this.medications = medications != null ? new ArrayList<>(medications) : new ArrayList<>();
        this.instructions = instructions;
        this.pharmacyName = pharmacyName;
        this.issueDate = issueDate;
        this.collectionDate = collectionDate;
    }

    public String getPrescriptionId() { return prescriptionId; }
    public void setPrescriptionId(String prescriptionId) { this.prescriptionId = prescriptionId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Clinician getPrescriber() { return prescriber; }
    public void setPrescriber(Clinician prescriber) { this.prescriber = prescriber; }

    public Appointment getAppointment() { return appointment; }
    public void setAppointment(Appointment appointment) { this.appointment = appointment; }

    public LocalDate getPrescriptionDate() { return prescriptionDate; }
    public void setPrescriptionDate(LocalDate prescriptionDate) { this.prescriptionDate = prescriptionDate; }

    public List<Medication> getMedications() { return medications; }
    public void setMedications(List<Medication> medications) {
        this.medications = medications != null ? new ArrayList<>(medications) : new ArrayList<>();
    }

    public void addMedication(Medication medication) {
        this.medications.add(medication);
    }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public String getPharmacyName() { return pharmacyName; }
    public void setPharmacyName(String pharmacyName) { this.pharmacyName = pharmacyName; }

    public LocalDate getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDate issueDate) { this.issueDate = issueDate; }

    public LocalDate getCollectionDate() { return collectionDate; }
    public void setCollectionDate(LocalDate collectionDate) { this.collectionDate = collectionDate; }
}
