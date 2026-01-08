package controllers;

import models.*;

import javax.swing.*;

import gui.PatientPanel;

import java.awt.*;

public class PatientRecordDialog extends JDialog {

    private final PatientPanel patientPanel = new PatientPanel();

    public PatientRecordDialog(Window owner) {
        super(owner, "Patient Record", ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(owner);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(patientPanel, BorderLayout.CENTER);
    }

    public void showRecord(PatientRecord record) {
        if (record == null) return;

        Patient patient = record.getPatient();
        if (patient != null) {
            patientPanel.setPatientInfo(patient);
        }

        // appointments
        String[] apptCols = {"Date", "Time", "Clinician", "Type", "Status"};
        ListTableModel<Appointment> apptModel = new ListTableModel<>(
                apptCols,
                record.getAppointments(),
                (a, col) -> {
                    switch (col) {
                        case 0: return a.getAppointmentDate();
                        case 1: return a.getAppointmentTime();
                        case 2: return a.getClinicianId(); // or resolve name via controller
                        case 3: return a.getAppointmentType();
                        case 4: return a.getStatus();
                        default: return "";
                    }
                },
                a -> {}
        );
        patientPanel.getAppointmentsTable().setModel(apptModel);

        // prescriptions
        String[] rxCols = {"Date", "Medication", "Dosage", "Status"};
        ListTableModel<Prescription> rxModel = new ListTableModel<>(
                rxCols,
                record.getPrescriptions(),
                (pr, col) -> {
                    switch (col) {
                        case 0: return pr.getPrescriptionDate();
                        case 1: return pr.getMedicationName();
                        case 2: return pr.getDosage();
                        case 3: return pr.getStatus();
                        default: return "";
                    }
                },
                pr -> {}
        );
        patientPanel.getPrescriptionsTable().setModel(rxModel);

        // referrals
        String[] refCols = {"Date", "Urgency", "Reason", "Status"};
        ListTableModel<Referral> refModel = new ListTableModel<>(
                refCols,
                record.getReferrals(),
                (r, col) -> {
                    switch (col) {
                        case 0: return r.getReferralDate();
                        case 1: return r.getUrgencyLevel();
                        case 2: return r.getReferralReason();
                        case 3: return r.getStatus();
                        default: return "";
                    }
                },
                r -> {}
        );
        patientPanel.getReferralsTable().setModel(refModel);
    }
}
