package models;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PatientRecord {
    private Patient patient;
    private List<Appointment> appointments;
    private List<Prescription> prescriptions;
    private List<Referral> referrals;

    public PatientRecord() {
        this.appointments = new ArrayList<>();
        this.prescriptions = new ArrayList<>();
        this.referrals = new ArrayList<>();
    }

    public PatientRecord(Patient patient,
                         ArrayList<Appointment> appointments,
                         ArrayList<Referral> referrals,
                         ArrayList<Prescription> prescriptions)
        {
        this.patient = patient;
        this.appointments = appointments;
        this.referrals = referrals;
        this.prescriptions = prescriptions;
    }


    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public List<Appointment> getAppointments() { return appointments; }
    public List<Prescription> getPrescriptions() { return prescriptions; }
    public List<Referral> getReferrals() { return referrals; }

    
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
