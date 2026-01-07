package services;

import java.util.ArrayList;

import models.Appointment;
import models.InAppData;
import models.Patient;
import models.PatientRecord;
import models.Prescription;
import models.Referral;

import java.util.ArrayList;

public class PatientRecordBuilder {

    private final InAppData data;

    public PatientRecordBuilder(InAppData data) {
        this.data = data;
    }

    public ArrayList<PatientRecord> buildAll() {
        ArrayList<PatientRecord> records = new ArrayList<>();

        for (Patient patient : data.getPatients()) {
            String pid = patient.getUserId();

            ArrayList<Appointment> appts = new ArrayList<>(
                    data.getAppointments().stream()
                    .filter(a -> a.getPatientId().equals(pid))
                    .toList()
                    );

            ArrayList<Prescription> prescs = new ArrayList<>(
                    data.getPrescriptions().stream()
                    .filter(p -> p.getPatientId().equals(pid))
                    .toList()
            );

            ArrayList<Referral> refs = new ArrayList<>(
                    data.getReferrals().stream()
                    .filter(r -> r.getPatientId().equals(pid))
                    .toList()
            );

            PatientRecord PR = new PatientRecord(patient.getPatientId(), appts, refs, prescs);
            records.add(PR);
            patient.setPatientRecord(PR);
        }

        return records;
    }

    public PatientRecord buildForPatient(String patientId) {
        Patient patient = data.getPatientById(patientId);
        if (patient == null) return null;

        ArrayList<Appointment> appts = new ArrayList<>(
                data.getAppointments().stream()
                .filter(a -> a.getPatientId().equals(patientId))
                .toList()
        );

        ArrayList<Prescription> prescs = new ArrayList<>(
                data.getPrescriptions().stream()
                .filter(p -> p.getPatientId().equals(patientId))
                .toList()
        );

        ArrayList<Referral> refs = new ArrayList<>(
                data.getReferrals().stream()
                .filter(r -> r.getPatientId().equals(patientId))
                .toList()
        );

        PatientRecord PR = new PatientRecord(patient.getPatientId(), appts, refs, prescs);
        patient.setPatientRecord(PR);
        return PR;
    }
}
