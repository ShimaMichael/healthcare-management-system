package controllers;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

import models.Patient;

public class PatientFormDialog extends JDialog {

    private JTextField txtPatientId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtNhsNumber;
    private JTextField txtGender;
    private JTextField txtDob;      // ISO yyyy-MM-dd
    private JTextField txtPostcode;
    private boolean confirmed;

    public PatientFormDialog() {
        this(null);
    }

    public PatientFormDialog(Patient existing) {
        setModal(true);
        setTitle(existing == null ? "Add Patient" : "Edit Patient");
        setSize(400, 300);
        setLocationRelativeTo(null);
        buildForm();

        if (existing != null) {
            txtPatientId.setText(existing.getPatientId());
            txtFirstName.setText(existing.getFirstName());
            txtLastName.setText(existing.getLastName());
            txtNhsNumber.setText(String.valueOf(existing.getNhsNumber()));
            txtGender.setText(existing.getGender());
            txtDob.setText(existing.getDateOfBirth().toString());
            txtPostcode.setText(existing.getPostcode());
        }
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        txtPatientId = new JTextField();
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtNhsNumber = new JTextField();
        txtGender = new JTextField();
        txtDob = new JTextField();
        txtPostcode = new JTextField();

        panel.add(new JLabel("Patient ID:"));
        panel.add(txtPatientId);
        panel.add(new JLabel("First name:"));
        panel.add(txtFirstName);
        panel.add(new JLabel("Last name:"));
        panel.add(txtLastName);
        panel.add(new JLabel("NHS number:"));
        panel.add(txtNhsNumber);
        panel.add(new JLabel("Gender:"));
        panel.add(txtGender);
        panel.add(new JLabel("DOB (yyyy-MM-dd):"));
        panel.add(txtDob);
        panel.add(new JLabel("Postcode:"));
        panel.add(txtPostcode);

        JPanel buttons = new JPanel();
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        buttons.add(btnOk);
        buttons.add(btnCancel);

        btnOk.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        btnCancel.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() { return confirmed; }

    public Patient getPatient() {
        String id = txtPatientId.getText().trim();
        String first = txtFirstName.getText().trim();
        String last = txtLastName.getText().trim();
        int nhs = Integer.parseInt(txtNhsNumber.getText().trim());
        String gender = txtGender.getText().trim();
        LocalDate dob = LocalDate.parse(txtDob.getText().trim());
        String postcode = txtPostcode.getText().trim();

        return new Patient(
                id, first, last, dob,
                "", "", // email, phone left blank here
                id, nhs, gender, "", postcode,
                "", "", LocalDate.now(), ""
        );
    }
}
