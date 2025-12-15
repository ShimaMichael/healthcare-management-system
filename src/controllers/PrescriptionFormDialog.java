import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrescriptionFormDialog extends JDialog {

    private JTextField txtPrescriptionId;
    private JTextField txtPatientId;
    private JTextField txtPrescriberId;
    private JTextField txtDate;
    private JTextField txtPharmacy;
    private JTextField txtInstructions;
    private JTextArea txtMedications; // one medication per line: name|form|strength

    private boolean confirmed;
    private Prescription existing;

    public PrescriptionFormDialog() {
        this(null);
    }

    public PrescriptionFormDialog(Prescription existing) {
        this.existing = existing;
        setModal(true);
        setTitle(existing == null ? "Add Prescription" : "Edit Prescription");
        setSize(500, 400);
        setLocationRelativeTo(null);
        buildForm();
        if (existing != null) populateFromExisting();
    }

    private void buildForm() {
        JPanel form = new JPanel(new GridLayout(0, 2, 5, 5));

        txtPrescriptionId = new JTextField();
        txtPatientId = new JTextField();
        txtPrescriberId = new JTextField();
        txtDate = new JTextField();
        txtPharmacy = new JTextField();
        txtInstructions = new JTextField();
        txtMedications = new JTextArea(5, 30);

        form.add(new JLabel("Prescription ID:"));
        form.add(txtPrescriptionId);
        form.add(new JLabel("Patient ID:"));
        form.add(txtPatientId);
        form.add(new JLabel("Prescriber ID:"));
        form.add(txtPrescriberId);
        form.add(new JLabel("Date (yyyy-MM-dd):"));
        form.add(txtDate);
        form.add(new JLabel("Pharmacy:"));
        form.add(txtPharmacy);
        form.add(new JLabel("Instructions:"));
        form.add(txtInstructions);

        JPanel medsPanel = new JPanel(new BorderLayout());
        medsPanel.setBorder(BorderFactory.createTitledBorder("Medications (name|form|strength per line)"));
        medsPanel.add(new JScrollPane(txtMedications), BorderLayout.CENTER);

        JPanel buttons = new JPanel();
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        buttons.add(btnOk);
        buttons.add(btnCancel);

        btnOk.addActionListener(e -> { confirmed = true; setVisible(false); });
        btnCancel.addActionListener(e -> { confirmed = false; setVisible(false); });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(form, BorderLayout.NORTH);
        getContentPane().add(medsPanel, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    private void populateFromExisting() {
        txtPrescriptionId.setText(existing.getPrescriptionId());
        txtPatientId.setText(existing.getPatient() != null ? existing.getPatient().getPatientId() : "");
        txtPrescriberId.setText(existing.getPrescriber() != null ? existing.getPrescriber().getClinicianId() : "");
        txtDate.setText(existing.getPrescriptionDate() != null ? existing.getPrescriptionDate().toString() : "");
        txtPharmacy.setText(existing.getPharmacyName());
        txtInstructions.setText(existing.getInstructions());

        StringBuilder sb = new StringBuilder();
        if (existing.getMedications() != null) {
            for (Medication m : existing.getMedications()) {
                sb.append(m.getName())
                  .append("|").append(m.getForm())
                  .append("|").append(m.getStrength())
                  .append(System.lineSeparator());
            }
        }
        txtMedications.setText(sb.toString());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public Prescription getPrescription() {
        String id = txtPrescriptionId.getText().trim();
        String patientId = txtPatientId.getText().trim();
        String prescriberId = txtPrescriberId.getText().trim();
        LocalDate date = LocalDate.parse(txtDate.getText().trim());
        String pharmacy = txtPharmacy.getText().trim();
        String instructions = txtInstructions.getText().trim();

        List<Medication> meds = new ArrayList<>();
        for (String line : txtMedications.getText().split("\\R")) {
            line = line.trim();
            if (line.isEmpty()) continue;
            String[] parts = line.split("\\|");
            String name = parts.length > 0 ? parts[0].trim() : "";
            String form = parts.length > 1 ? parts[1].trim() : "";
            String strength = parts.length > 2 ? parts[2].trim() : "";
            meds.add(new Medication(null, name, form, strength));
        }

        Prescription p = new Prescription();
        p.setPrescriptionId(id);
        p.setPrescriptionDate(date);
        p.setPharmacyName(pharmacy);
        p.setInstructions(instructions);
        p.setMedications(meds);
        p.setIssueDate(date);
        p.setCollectionDate(null);

        // Patient/Clinician to be resolved from IDs by higher-level service if needed
        p.setPatient(null);
        p.setPrescriber(null);
        p.setAppointment(null);

        return p;
    }
}
