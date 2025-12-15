import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ClinicianController {

    private final ClinicianPanel view;
    private final List<Clinician> clinicians;
    private final ListTableModel<Clinician> tableModel;

    public ClinicianController(ClinicianPanel view) {
        this.view = view;
        this.clinicians = new ArrayList<>();

        String[] columns = { "Clinician ID", "Name", "Title", "Speciality", "Workplace", "Status" };

        this.tableModel = new ListTableModel<>(
                columns,
                clinicians,
                (clinician, col) -> {
                    switch (col) {
                        case 0: return clinician.getClinicianId();
                        case 1: return clinician.getFullName();
                        case 2: return clinician.getTitle();
                        case 3: return clinician.getSpeciality();
                        case 4: return clinician.getWorkplaceId();
                        case 5: return clinician.getEmploymentStatus();
                        default: return "";
                    }
                },
                c -> {} // no extra side effects on update for now
        );

        view.getTable().setModel(tableModel);
        addListeners();
    }

    private void addListeners() {
        view.getBtnAdd().addActionListener(e -> onAddClinician());
        view.getBtnEdit().addActionListener(e -> onEditClinician());
        view.getBtnDelete().addActionListener(e -> onDeleteClinician());
    }

    private void onAddClinician() {
        ClinicianFormDialog dialog = new ClinicianFormDialog();
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Clinician c = dialog.getClinician();
            tableModel.addRow(c);
            // TODO: append to clinicians data file
        }
    }

    private void onEditClinician() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a clinician first");
            return;
        }
        Clinician existing = tableModel.getRow(row);
        ClinicianFormDialog dialog = new ClinicianFormDialog(existing);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Clinician updated = dialog.getClinician();
            tableModel.updateRow(row, updated);
            // TODO: rewrite clinicians data file
        }
    }

    private void onDeleteClinician() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a clinician first");
            return;
        }
        tableModel.removeRow(row);
        // TODO: rewrite clinicians data file
    }
}
