package controllers;

import models.*;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gui.ClinicianPanel;

public class ClinicianController {

 private final ClinicianPanel view;
    private final InAppData data;
    private final Clinician clinician;

    private ListTableModel<Appointment> apptModel;
    private ListTableModel<Prescription> rxModel;
    private ListTableModel<Referral> refModel;

    public ClinicianController(ClinicianPanel view,
                                        InAppData data,
                                        String clinicianId) {
        this.view = view;
        this.data = data;
        this.clinician = data.getClinicianById(clinicianId);

        if (clinician == null) {
            JOptionPane.showMessageDialog(view,
                    "Clinician not found: " + clinicianId,
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        String workplaceName = resolveWorkplaceName(clinician.getWorkplaceId());
        view.setClinicianInfo(clinician, workplaceName);

        apptModel = buildAppointmentsModel();
        rxModel   = buildPrescriptionsModel();
        refModel  = buildReferralsModel();

        view.getAppointmentsTable().setModel(apptModel);
        view.getPrescriptionsTable().setModel(rxModel);
        view.getReferralsTable().setModel(refModel);

        addListeners();
    }

    private String resolveWorkplaceName(String facilityId) {
        Facility f = data.getFacilityById(facilityId);
        return f != null ? f.getName() : facilityId;
    }

    private ListTableModel<Appointment> buildAppointmentsModel() {
        List<Appointment> mine = data.getAppointments().stream()
                .filter(a -> a.getClinicianId().equals(clinician.getClinicianId()))
                .toList();

        String[] cols = { "Date", "Time", "Patient", "Type", "Status" };

        return new ListTableModel<>(
                cols,
                new ArrayList<>(mine),
                (a, col) -> {
                    switch (col) {
                        case 0: return a.getAppointmentDate();
                        case 1: return a.getAppointmentTime();
                        case 2: {
                            Patient p = data.getPatientById(a.getPatientId());
                            return p != null ? p.getFullName() : a.getPatientId();
                        }
                        case 3: return a.getAppointmentType();
                        case 4: return a.getStatus();
                        default: return "";
                    }
                },
                a -> {}
        );
    }

    private ListTableModel<Prescription> buildPrescriptionsModel() {
        List<Prescription> mine = data.getPrescriptions().stream()
                .filter(p -> p.getClinicianId().equals(clinician.getClinicianId()))
                .toList();

        String[] cols = { "Date", "Patient", "Medication", "Dosage", "Status" };

        return new ListTableModel<>(
                cols,
                new ArrayList<>(mine),
                (p, col) -> {
                    switch (col) {
                        case 0: return p.getPrescriptionDate();
                        case 1: {
                            Patient pt = data.getPatientById(p.getPatientId());
                            return pt != null ? pt.getFullName() : p.getPatientId();
                        }
                        case 2: return p.getMedicationName();
                        case 3: return p.getDosage();
                        case 4: return p.getStatus();
                        default: return "";
                    }
                },
                p -> {}
        );
    }

    private ListTableModel<Referral> buildReferralsModel() {
        List<Referral> mine = data.getReferrals().stream()
                .filter(r -> r.getReferringClinicianId().equals(clinician.getClinicianId())
                          || r.getReferredClinicianId().equals(clinician.getClinicianId()))
                .toList();

        String[] cols = { "Date", "Patient", "Direction", "Other Clinician", "Reason", "Status" };

        return new ListTableModel<>(
                cols,
                new ArrayList<>(mine),
                (r, col) -> {
                    switch (col) {
                        case 0: return r.getReferralDate();
                        case 1: {
                            Patient pt = data.getPatientById(r.getPatientId());
                            return pt != null ? pt.getFullName() : r.getPatientId();
                        }
                        case 2:
                            return r.getReferringClinicianId().equals(clinician.getClinicianId())
                                    ? "Referred by me" : "Referred to me";
                        case 3: {
                            String otherId = r.getReferringClinicianId().equals(clinician.getClinicianId())
                                    ? r.getReferredClinicianId()
                                    : r.getReferringClinicianId();
                            Clinician other = data.getClinicianById(otherId);
                            return other != null ? other.getFullName() : otherId;
                        }
                        case 4: return r.getReferralReason();
                        case 5: return r.getStatus();
                        default: return "";
                    }
                },
                r -> {}
        );
    }

    private void addListeners() {
        view.getBtnNewPrescription().addActionListener(e -> onNewPrescription());
        view.getBtnDeletePrescription().addActionListener(e -> onDeletePrescription());
        view.getBtnNewReferral().addActionListener(e -> onNewReferral());
        view.getBtnDeleteReferral().addActionListener(e -> onDeleteReferral());
    }

    private void onNewPrescription() {
        // prescriber fixed to this clinician; patient typed in dialog
        PrescriptionFormDialog dialog =
                new PrescriptionFormDialog(clinician.getClinicianId(), null);
        dialog.setVisible(true);
        if (!dialog.isConfirmed()) return;

        Prescription p = dialog.getPrescription();

        // add to central model
        data.getPrescriptions().add(p);

        // if this prescription belongs to this clinician, show it in the table
        if (clinician.getClinicianId().equals(p.getClinicianId())) {
            rxModel.addRow(p);
        }
    }

    private void onDeletePrescription() {
        int row = view.getPrescriptionsTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a prescription first");
            return;
        }

        Prescription p = rxModel.getRow(row);

        // simple rule: only delete your own prescriptions
        if (!clinician.getClinicianId().equals(p.getClinicianId())) {
            JOptionPane.showMessageDialog(view,
                    "You can only delete prescriptions you created.");
            return;
        }

        data.getPrescriptions().remove(p);
        rxModel.removeRow(row);
    }

    private void onNewReferral() {
        // patientId could come from a selected appointment row; null for free entry:
        ReferralFormDialog dialog =
                new ReferralFormDialog(clinician.getClinicianId(), null);
        dialog.setVisible(true);
        if (!dialog.isConfirmed()) return;

        Referral r = dialog.getReferral();

        // central model
        data.getReferrals().add(r);

        // enqueue + process via singleton
        ReferralManager mgr = ReferralManager.getInstance();
        mgr.enqueueReferral(r);
        mgr.processNextReferral();

        // reflect in this clinician's table
        refModel.addRow(r);
    }
    private void onDeleteReferral() { 
        int row = view.getReferralsTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a referral first");
            return;
        }

        Referral r = refModel.getRow(row);

        if (!clinician.getClinicianId().equals(r.getReferringClinicianId())) {
            JOptionPane.showMessageDialog(view,
                    "You can only delete referrals you created.");
            return;
        }

        data.getReferrals().remove(r);
        refModel.removeRow(row);
    }
}