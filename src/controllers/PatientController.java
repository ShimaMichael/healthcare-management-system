import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PatientController {

    private final PatientPanel view;
    private final List<Patient> patients;
    private final ListTableModel<Patient> tableModel;

    public PatientController(PatientPanel view) {
        this.view = view;
        this.patients = new ArrayList<>();

        String[] columns = { "ID", "Name", "NHS", "Gender", "DOB", "Postcode" };

        this.tableModel = new ListTableModel<>(
                columns,
                patients,
                (patient, col) -> {
                    switch (col) {
                        case 0: return patient.getPatientId();
                        case 1: return patient.getFullName();
                        case 2: return patient.getNhsNumber();
                        case 3: return patient.getGender();
                        case 4: return patient.getDateOfBirth();
                        case 5: return patient.getPostcode();
                        default: return "";
                    }
                },
                p -> {} // not used for now
        );

        view.getTable().setModel(tableModel);
        addListeners();
    }

    private void addListeners() {
        view.getBtnAdd().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onAddPatient();
            }
        });

        view.getBtnEdit().addActionListener(e -> onEditPatient());
        view.getBtnDelete().addActionListener(e -> onDeletePatient());
    }

    private void onAddPatient() {
        PatientFormDialog dialog = new PatientFormDialog();
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Patient p = dialog.getPatient();
            tableModel.addRow(p);
            // TODO: persist to file
        }
    }

    private void onEditPatient() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a patient first");
            return;
        }
        Patient existing = tableModel.getRow(row);
        PatientFormDialog dialog = new PatientFormDialog(existing);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Patient updated = dialog.getPatient();
            tableModel.updateRow(row, updated);
            // TODO: persist to file
        }
    }

    private void onDeletePatient() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a patient first");
            return;
        }
        tableModel.removeRow(row);
        // TODO: update file
    }
}
