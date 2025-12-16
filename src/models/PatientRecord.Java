import java.util.ArrayList;
import java.util.List;

public class PatientRecord {
    private String recordId;
    private Patient patient;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private List<Referral> referrals;
    private String allergies;
    private String pastMedicalHistory;

    public PatientRecord() {
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.referrals = new ArrayList<>();
    }

    public PatientRecord(String recordId,
                         Patient patient,
                         String allergies,
                         String pastMedicalHistory) {
        this();
        this.recordId = recordId;
        this.patient = patient;
        this.allergies = allergies;
        this.pastMedicalHistory = pastMedicalHistory;
    }

    public String getRecordId() { return recordId; }
    public void setRecordId(String recordId) { this.recordId = recordId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public List<Appointment> getAppointments() { return appointments; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public List<Referral> getReferrals() { return referrals; }

    public String getAllergies() { return allergies; }
    public void setAllergies(String allergies) { this.allergies = allergies; }

    public String getPastMedicalHistory() { return pastMedicalHistory; }
    public void setPastMedicalHistory(String pastMedicalHistory) { this.pastMedicalHistory = pastMedicalHistory; }

    public void addAppointment(Appointment appointment) {
        this.appointments.add(appointment);
    }

    public void addPrescription(Prescription prescription) {
        this.prescriptions.add(prescription);
    }

    public void addReferral(Referral referral) {
        this.referrals.add(referral);
    }
}
