package controllers;
import gui.PrescriptionPanel;
import models.Prescription;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionController {

    private final PrescriptionPanel view;
    private final List<Prescription> prescriptions;
    private final ListTableModel<Prescription> tableModel;

    public PrescriptionController(PrescriptionPanel view) {
        this.view = view;
        this.prescriptions = new ArrayList<>();

        String[] columns = { "ID", "Patient", "Prescriber", "Date", "Pharmacy", "Med count" };

        this.tableModel = new ListTableModel<>(
                columns,
                prescriptions,
                (p, col) -> {
                    switch (col) {
                        case 0: return p.getPrescriptionId();
                        case 1: return p.getPatient() != null ? p.getPatient().getFullName() : "";
                        case 2: return p.getPrescriber() != null ? p.getPrescriber().getFullName() : "";
                        case 3: return p.getPrescriptionDate();
                        case 4: return p.getPharmacyName();
                        case 5: return p.getMedications() != null ? p.getMedications().size() : 0;
                        default: return "";
                    }
                },
                x -> {}
        );

        view.getTable().setModel(tableModel);
        addListeners();
    }

    private void addListeners() {
        view.getBtnAdd().addActionListener(e -> onAddPrescription());
        view.getBtnEdit().addActionListener(e -> onEditPrescription());
        view.getBtnDelete().addActionListener(e -> onDeletePrescription());
    }

    private void onAddPrescription() {
        PrescriptionFormDialog dialog = new PrescriptionFormDialog();
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Prescription p = dialog.getPrescription();
            tableModel.addRow(p);
            // TODO: append to prescription file
        }
    }

    private void onEditPrescription() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a prescription first");
            return;
        }
        Prescription existing = tableModel.getRow(row);
        PrescriptionFormDialog dialog = new PrescriptionFormDialog(existing);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Prescription updated = dialog.getPrescription();
            tableModel.updateRow(row, updated);
            // TODO: rewrite prescription file
        }
    }

    private void onDeletePrescription() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a prescription first");
            return;
        }
        tableModel.removeRow(row);
        // TODO: update prescription file
    }
}
