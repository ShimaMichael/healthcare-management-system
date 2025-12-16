package controllers;

import gui.AppointmentPanel;
import models.Appointment;

import javax.swing.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentController {

    private final AppointmentPanel view;
    private final List<Appointment> appointments;
    private final ListTableModel<Appointment> tableModel;

    public AppointmentController(AppointmentPanel view) {
        this.view = view;
        this.appointments = new ArrayList<>();

        String[] columns = { "ID", "Patient", "Clinician", "Facility", "Start", "Duration", "Status" };

        this.tableModel = new ListTableModel<>(
                columns,
                appointments,
                (appointment, col) -> {
                    switch (col) {
                        case 0: return appointment.getAppointmentId();
                        case 1: return appointment.getPatient() != null ? appointment.getPatient().getFullName() : "";
                        case 2: return appointment.getClinician() != null ? appointment.getClinician().getFullName() : "";
                        case 3: return appointment.getFacility() != null ? appointment.getFacility().getName() : "";
                        case 4: return appointment.getStartDateTime();
                        case 5: return appointment.getDurationMinutes();
                        case 6: return appointment.getStatus();
                        default: return "";
                    }
                },
                a -> {}
        );

        view.getTable().setModel(tableModel);
        addListeners();
    }

    private void addListeners() {
        view.getBtnAdd().addActionListener(e -> onAddAppointment());
        view.getBtnEdit().addActionListener(e -> onEditAppointment());
        view.getBtnDelete().addActionListener(e -> onDeleteAppointment());
    }

    private void onAddAppointment() {
        AppointmentFormDialog dialog = new AppointmentFormDialog();
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Appointment a = dialog.getAppointment();
            tableModel.addRow(a);
            // TODO: append to appointments file
        }
    }

    private void onEditAppointment() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select an appointment first");
            return;
        }
        Appointment existing = tableModel.getRow(row);
        AppointmentFormDialog dialog = new AppointmentFormDialog(existing);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Appointment updated = dialog.getAppointment();
            tableModel.updateRow(row, updated);
            // TODO: rewrite appointments file
        }
    }

    private void onDeleteAppointment() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select an appointment first");
            return;
        }
        tableModel.removeRow(row);
        // TODO: update appointments file
    }
}
