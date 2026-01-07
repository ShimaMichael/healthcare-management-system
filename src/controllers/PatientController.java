package controllers;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gui.PatientPanel;
import models.*;

import javax.swing.*;
import java.util.ArrayList;

public class PatientController {

    private final PatientPanel view;
    private final InAppData data;
    private final String patientId;

    public PatientController(PatientPanel view,
                                      InAppData data,
                                      String patientId) {
        this.view = view;
        this.data = data;
        this.patientId = patientId;

        init();
    }

    private void init() {
        Patient patient = data.getPatientById(patientId);
        if (patient == null) {
            JOptionPane.showMessageDialog(view,
                    "Patient not found: " + patientId,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        view.setPatientInfo(patient);

        PatientRecord record = data.getRecordById(patientId);
        if (record == null) {
            JOptionPane.showMessageDialog(view,
                    "No record found for patient: " + patientId,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        setupAppointmentsTable(record);
        setupPrescriptionsTable(record);
        setupReferralsTable(record);
    }

    private void setupAppointmentsTable(PatientRecord record) {
        String[] cols = {"Date", "Time", "Type", "Status"};

        ListTableModel<Appointment> model = new ListTableModel<>(
                cols,
                new ArrayList<>(record.getAppointments()),
                (a, col) -> {
                    switch (col) {
                        case 0: return a.getAppointmentDate();
                        case 1: return a.getAppointmentTime();
                        case 2: return a.getAppointmentType();
                        case 3: return a.getStatus();
                        default: return "";
                    }
                },
                a -> {} // read-only for patients
        );

        view.getAppointmentsTable().setModel(model);
    }

    private void setupPrescriptionsTable(PatientRecord record) {
        String[] cols = {"Date", "Medication", "Dosage", "Status"};

        ListTableModel<Prescription> model = new ListTableModel<>(
                cols,
                new ArrayList<>(record.getPrescriptions()),
                (p, col) -> {
                    switch (col) {
                        case 0: return p.getPrescriptionDate();
                        case 1: return p.getMedicationName();
                        case 2: return p.getDosage();
                        case 3: return p.getStatus();
                        default: return "";
                    }
                },
                p -> {} // read-only
        );

        view.getPrescriptionsTable().setModel(model);
    }

    private void setupReferralsTable(PatientRecord record) {
        String[] cols = {"Date", "Urgency", "Reason", "Status"};

        ListTableModel<Referral> model = new ListTableModel<>(
                cols,
                new ArrayList<>(record.getReferrals()),
                (r, col) -> {
                    switch (col) {
                        case 0: return r.getReferralDate();
                        case 1: return r.getUrgencyLevel();
                        case 2: return r.getReferralReason();
                        case 3: return r.getStatus();
                        default: return "";
                    }
                },
                r -> {} // read-only
        );

        view.getReferralsTable().setModel(model);
    }
}


// public class PatientController {

//     private final PatientPanel view;
//     private final InAppData inAppData;
//     private final ListTableModel<Patient> tableModel;

//     public PatientController(PatientPanel view, InAppData inAppData) {
//         this.view = view;
//         this.inAppData = inAppData;

//         String[] columns = { "ID", "Name", "NHS", "Gender", "DOB", "Postcode" };

//         this.tableModel = new ListTableModel<>(
//                 columns,
//                 inAppData.getPatients(),
//                 (patient, col) -> {
//                     switch (col) {
//                         case 0: return patient.getPatientId();
//                         case 1: return patient.getFullName();
//                         case 2: return patient.getNhsNumber();
//                         case 3: return patient.getGender();
//                         case 4: return patient.getDateOfBirth();
//                         case 5: return patient.getPostcode();
//                         default: return "";
//                     }
//                 },
//                 p -> {} // not used for now
//         );

//         view.getTable().setModel(tableModel);
//         addListeners();
//     }

//     private void addListeners() {
//         view.getBtnAdd().addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 onAddPatient();
//             }
//         });

//         view.getBtnEdit().addActionListener(e -> onEditPatient());
//         view.getBtnDelete().addActionListener(e -> onDeletePatient());
//     }

//     private void onAddPatient() {
//         PatientFormDialog dialog = new PatientFormDialog();
//         dialog.setVisible(true);
//         if (dialog.isConfirmed()) {
//             Patient p = dialog.getPatient();
//             tableModel.addRow(p);
//             inAppData.addPatient(p);
//         }
//     }

//     private void onEditPatient() {
//         int row = view.getTable().getSelectedRow();
//         if (row < 0) {
//             JOptionPane.showMessageDialog(view, "Select a patient first");
//             return;
//         }

//         Patient existing = tableModel.getRow(row);
//         PatientFormDialog dialog = new PatientFormDialog(existing);
//         dialog.setVisible(true);

//         if (dialog.isConfirmed()) {
//             Patient updated = dialog.getPatient();
//             updated.setPatientId(existing.getPatientId());
//             inAppData.updatePatient(updated);
//             tableModel.updateRow(row, updated);
//         }
//     }


//     private void onDeletePatient() {
//         int viewRow = view.getTable().getSelectedRow();
//         if (viewRow < 0) {
//             JOptionPane.showMessageDialog(view, "Select a patient first");
//             return;
//         }

//         int modelRow = view.getTable().convertRowIndexToModel(viewRow);
//         if (modelRow < 0 || modelRow >= tableModel.getRowCount()) {
//             return; // safety
//         }

//         Patient toDelete = tableModel.getRow(modelRow);

//         int choice = JOptionPane.showConfirmDialog(
//                 view,
//                 "Delete patient " + toDelete.getFullName() + "?",
//                 "Confirm delete",
//                 JOptionPane.YES_NO_OPTION
//         );
//         if (choice != JOptionPane.YES_OPTION) {
//             return;
//         }

//         inAppData.removePatient(toDelete.getPatientId());
//         tableModel.removeRow(modelRow);
//     }


// }
