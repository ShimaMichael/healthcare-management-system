package models;

import java.util.ArrayList;
import java.lang.String;
import java.sql.Ref;

public class InAppData {

    private ArrayList<Patient> patients;
    private ArrayList<Clinician> clinicians;
    private ArrayList<Facility> facilities;
    private ArrayList<Appointment> appointments;
    private ArrayList<Prescription> prescriptions;
    private ArrayList<Referral> referrals;
    private ArrayList<AdminStaff> adminStaff;
    private ArrayList<PatientRecord> records;

    public InAppData(ArrayList<Patient> patients,
                            ArrayList<Clinician> clinicians,
                            ArrayList<Facility> facilities,
                            ArrayList<Appointment> appointments,
                            ArrayList<Prescription> prescriptions,
                            ArrayList<Referral> referrals,
                            ArrayList<AdminStaff> adminStaff){
        this.patients = patients;
        this.clinicians = clinicians;
        this.facilities = facilities;
        this.appointments = appointments;
        this.prescriptions = prescriptions;
        this.referrals = referrals;
        this.adminStaff = adminStaff;
        this.records = null;
    }

    //region Patients

    public ArrayList<Patient> getPatients() { return patients; }

    public Patient getPatientById(String id) {
        for (Patient p : patients) {
            if (p.getUserId().equals(id)) return p;
        }
        return null;
    }

    public void addPatient(Patient p) {
        patients.add(p);
    }

    public void updatePatient(Patient updated) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getUserId().equals(updated.getUserId())) {
                patients.set(i, updated);
                return;
            }
        }
    }

    public void removePatient(String id) {
        patients.removeIf(p -> p.getUserId().equals(id));
    }

    //endregion

    //region Clinicians
    public ArrayList<Clinician> getClinicians() { return clinicians; }

    public Clinician getClinicianById(String id) {
        for (Clinician c : clinicians) {
            if (c.getUserId().equals(id)) return c;
        }
        return null;
    }

    public void addClinician(Clinician c) {
        clinicians.add(c);
    }

    public void updateClinician(Clinician updated) {
        for (int i = 0; i < clinicians.size(); i++) {
            if (clinicians.get(i).getUserId().equals(updated.getUserId())) {
                clinicians.set(i, updated);
                return;
            }
        }
    }

    public void removeClinician(String id) {
        clinicians.removeIf(c -> c.getUserId().equals(id));
    }
    //endregion

    //region Facilities\

    public ArrayList<Facility> getFacilities() { return facilities; }

    public Facility getFacilityById(String id) {
        for (Facility f : facilities) {
            if (f.getFacilityId().equals(id)) return f;
        }
        return null;
    }

    public void addFacility(Facility f) {
        facilities.add(f);
    }

    public void updateFacility(Facility updated) {
        for (int i = 0; i < facilities.size(); i++) {
            if (facilities.get(i).getFacilityId().equals(updated.getFacilityId())) {
                facilities.set(i, updated);
                return;
            }
        }
    }

    public void removeFacility(String id) {
        facilities.removeIf(f -> f.getFacilityId().equals(id));
    }
    //endregion

    //region Appointments
    public ArrayList<Appointment> getAppointments() { return appointments; }

     public Appointment getAppointmentById(String id) {
        for (Appointment a : appointments) {
            if (a.getAppointmentId().equals(id)) return a;
        }
        return null;
    }

    public void addAppointment(Appointment a) {
        appointments.add(a);
    }

    public void updateAppointment(Appointment updated) {
        for (int i = 0; i < appointments.size(); i++) {
            if (appointments.get(i).getAppointmentId().equals(updated.getAppointmentId())) {
                appointments.set(i, updated);
                return;
            }
        }
    }

    public void removeAppointment(String id) {
        appointments.removeIf(a -> a.getAppointmentId().equals(id));
    }

    //endregion

    //region Prescriptions
    public ArrayList<Prescription> getPrescriptions() { return prescriptions; }

     public Prescription getPrescriptionById(String id) {
        for (Prescription p : prescriptions) {
            if (p.getPrescriptionId().equals(id)) return p;
        }
        return null;
    }

    public void addPrescription(Prescription p) {
        prescriptions.add(p);
    }

    public void updatePrescription(Prescription updated) {
        for (int i = 0; i < prescriptions.size(); i++) {
            if (prescriptions.get(i).getPrescriptionId().equals(updated.getPrescriptionId())) {
                prescriptions.set(i, updated);
                return;
            }
        }
    }

    public void removePrescription(String id) {
        prescriptions.removeIf(p -> p.getPrescriptionId().equals(id));
    }
    //endregion

    //region Referrals
    public ArrayList<Referral> getReferrals() { return referrals; }

     public Referral getReferralById(String id) {
        for (Referral r : referrals) {
            if (r.getReferralId().equals(id)) return r;
        }
        return null;
    }

    public void addReferral(Referral r) {
        referrals.add(r);
    }

    public void updateReferral(Referral updated) {
        for (int i = 0; i < referrals.size(); i++) {
            if (referrals.get(i).getReferralId().equals(updated.getReferralId())) {
                referrals.set(i, updated);
                return;
            }
        }
    }

    public void removeReferral(String id) {
        referrals.removeIf(r -> r.getReferralId().equals(id));
    }
    //endregion

    //region AdminStaff
    public ArrayList<AdminStaff> getAdminStaff() { return adminStaff; }

     public AdminStaff getAdminStaffById(String id) {
        for (AdminStaff a : adminStaff) {
            if (a.getUserId().equals(id)) return a;
        }
        return null;
    }

    public void addAdminStaff(AdminStaff a) {
        adminStaff.add(a);
    }

    public void updateAdminStaff(AdminStaff updated) {
        for (int i = 0; i < adminStaff.size(); i++) {
            if (adminStaff.get(i).getUserId().equals(updated.getUserId())) {
                adminStaff.set(i, updated);
                return;
            }
        }
    }

    public void removeAdminStaff(String id) {
        adminStaff.removeIf(a -> a.getUserId().equals(id));
    }
    //endregion

    //region PatientRecords
    public void setRecords(ArrayList<PatientRecord> records) { this.records = records; }

    public ArrayList<PatientRecord> getRecords() { return records; }

    public PatientRecord getRecordById(String id) {
        if (records == null) return null;
        for (PatientRecord r : records) {
            if (r.getPatient().equals(id)) return r;
        }
        return null;
    }

    public void addRecord(PatientRecord r) {
        records.add(r);
    }

    public void updateRecord(PatientRecord updated) {
        for (int i = 0; i < records.size(); i++) {
            if (records.get(i).getPatient().equals(updated.getPatient())) {
                records.set(i, updated);
                return;
            }
        }
    }

    public void removeRecord(String id) {
        records.removeIf(r -> r.getPatient().equals(id));
    }
    //endregion
}
