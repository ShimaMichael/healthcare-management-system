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
    private ListTableModel<Appointment> apptModel;


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
        addAppointmentListeners();
    }

    private void addAppointmentListeners() {
        view.getBtnNewAppt().addActionListener(e -> onNewAppointment());
        view.getBtnEditAppt().addActionListener(e -> onEditAppointment());
        view.getBtnCancelAppt().addActionListener(e -> onCancelAppointment());
    }


    private void setupAppointmentsTable(PatientRecord record) {
        String[] cols = {"Date", "Time", "Type", "Status", "Clinician"};

         apptModel = new ListTableModel<>(
                cols,
                new ArrayList<>(record.getAppointments()),
                (a, col) -> {
                    switch (col) {
                        case 0: return a.getAppointmentDate();
                        case 1: return a.getAppointmentTime();
                        case 2: return a.getAppointmentType();
                        case 3: return a.getStatus();
                        case 4: {
                            Clinician c = data.getClinicianById(a.getClinicianId());
                            return c != null ? c.getFullName() : a.getClinicianId();
                        }
                        default: return "";
                    }
                },
                a -> {} // read-only for patients
        );

        view.getAppointmentsTable().setModel(apptModel);
    }

    private void onNewAppointment() {
        AppointmentFormDialog dialog =
                new AppointmentFormDialog(patientId); 
        dialog.setVisible(true);
        if (!dialog.isConfirmed()) return;

        Appointment a = dialog.getAppointment();

        data.getAppointments().add(a);

        PatientRecord rec = data.getRecordById(patientId);
        if (rec != null) rec.getAppointments().add(a);

        apptModel.addRow(a);
    }

    private void onEditAppointment() {
        int row = view.getAppointmentsTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select an appointment first");
            return;
        }

        Appointment existing = apptModel.getRow(row);
        AppointmentFormDialog dialog = new AppointmentFormDialog(existing);
        dialog.setVisible(true);
        if (!dialog.isConfirmed()) return;

        Appointment updated = dialog.getAppointment();

        // keep same id, patientId, clinicianId if you want
        updated.setAppointmentId(existing.getAppointmentId());
        if (updated.getPatientId() == null)
            updated.setPatientId(existing.getPatientId());

        apptModel.updateRow(row, updated);
    }

    private void onCancelAppointment() {
        int row = view.getAppointmentsTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view,
                    "Select an appointment first");
            return;
        }

        int choice = JOptionPane.showConfirmDialog(
                view,
                "Cancel this appointment?",
                "Confirm cancel",
                JOptionPane.YES_NO_OPTION
        );
        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        Appointment a = apptModel.getRow(row);

        a.setStatus("Cancelled");

        apptModel.updateRow(row, a);
    }



    private void setupPrescriptionsTable(PatientRecord record) {
        String[] cols = {"Date", "Medication", "Dosage", "Status", "Clinician"};

        ListTableModel<Prescription> model = new ListTableModel<>(
                cols,
                new ArrayList<>(record.getPrescriptions()),
                (p, col) -> {
                    switch (col) {
                        case 0: return p.getPrescriptionDate();
                        case 1: return p.getMedicationName();
                        case 2: return p.getDosage();
                        case 3: return p.getStatus();
                        case 4: {
                            Clinician c = data.getClinicianById(p.getClinicianId());
                            return c != null ? c.getFullName() : p.getClinicianId();
                        }
                        default: return "";
                    }
                },
                p -> {} // read-only
        );

        view.getPrescriptionsTable().setModel(model);
    }


    private void setupReferralsTable(PatientRecord record) {
        String[] cols = {"Date", "Urgency", "Reason", "Status", "Referred To"};

        ListTableModel<Referral> model = new ListTableModel<>(
                cols,
                new ArrayList<>(record.getReferrals()),
                (r, col) -> {
                    switch (col) {
                        case 0: return r.getReferralDate();
                        case 1: return r.getUrgencyLevel();
                        case 2: return r.getReferralReason();
                        case 3: return r.getStatus();
                        case 4: {
                            Clinician c = data.getClinicianById(r.getReferringClinicianId());
                            return c != null ? c.getFullName() : r.getReferringClinicianId();
                        }
                        default: return "";
                    }
                },
                r -> {} // read-only
        );

        view.getReferralsTable().setModel(model);
    }
}