package controllers;

import models.Clinician;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class ClinicianFormDialog extends JDialog {

    private JTextField txtUserId;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtDob;
    private JTextField txtEmail;
    private JTextField txtPhone;

    private JTextField txtClinicianId;
    private JTextField txtTitle;
    private JTextField txtSpeciality;
    private JTextField txtWorkplaceId;
    private JTextField txtWorkplaceType;
    private JTextField txtEmploymentStatus;
    private JTextField txtStartDate;
    private JTextField txtGmcNumber;

    private boolean confirmed;

    public ClinicianFormDialog() {
        this(null);
    }

    public ClinicianFormDialog(Clinician existing) {
        setModal(true);
        setTitle(existing == null ? "Add Clinician" : "Edit Clinician");
        setSize(500, 400);
        setLocationRelativeTo(null);
        buildForm();

        if (existing != null) {
            txtUserId.setText(existing.getUserId());
            txtFirstName.setText(existing.getFirstName());
            txtLastName.setText(existing.getLastName());
            txtDob.setText(existing.getDateOfBirth().toString());
            txtEmail.setText(existing.getEmail());
            txtPhone.setText(existing.getPhone());

            txtClinicianId.setText(existing.getClinicianId());
            txtTitle.setText(existing.getTitle());
            txtSpeciality.setText(existing.getSpeciality());
            txtWorkplaceId.setText(existing.getWorkplaceId());
            txtWorkplaceType.setText(existing.getWorkplaceType());
            txtEmploymentStatus.setText(existing.getEmploymentStatus());
            txtStartDate.setText(existing.getStartDate().toString());
            txtGmcNumber.setText(String.valueOf(existing.getGmcNumber()));
        }
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        txtUserId = new JTextField();
        txtFirstName = new JTextField();
        txtLastName = new JTextField();
        txtDob = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        txtClinicianId = new JTextField();
        txtTitle = new JTextField();
        txtSpeciality = new JTextField();
        txtWorkplaceId = new JTextField();
        txtWorkplaceType = new JTextField();
        txtEmploymentStatus = new JTextField();
        txtStartDate = new JTextField();
        txtGmcNumber = new JTextField();

        panel.add(new JLabel("User ID:"));
        panel.add(txtUserId);
        panel.add(new JLabel("First name:"));
        panel.add(txtFirstName);
        panel.add(new JLabel("Last name:"));
        panel.add(txtLastName);
        panel.add(new JLabel("DOB (yyyy-MM-dd):"));
        panel.add(txtDob);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Phone:"));
        panel.add(txtPhone);

        panel.add(new JLabel("Clinician ID:"));
        panel.add(txtClinicianId);
        panel.add(new JLabel("Title:"));
        panel.add(txtTitle);
        panel.add(new JLabel("Speciality:"));
        panel.add(txtSpeciality);
        panel.add(new JLabel("Workplace ID:"));
        panel.add(txtWorkplaceId);
        panel.add(new JLabel("Workplace type:"));
        panel.add(txtWorkplaceType);
        panel.add(new JLabel("Employment status:"));
        panel.add(txtEmploymentStatus);
        panel.add(new JLabel("Start date (yyyy-MM-dd):"));
        panel.add(txtStartDate);
        panel.add(new JLabel("GMC number:"));
        panel.add(txtGmcNumber);

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
        getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    public boolean isConfirmed() { return confirmed; }

    public Clinician getClinician() {
        String userId = txtUserId.getText().trim();
        String firstName = txtFirstName.getText().trim();
        String lastName = txtLastName.getText().trim();
        LocalDate dob = LocalDate.parse(txtDob.getText().trim());
        String email = txtEmail.getText().trim();
        String phone = txtPhone.getText().trim();

        String clinicianId = txtClinicianId.getText().trim();
        String title = txtTitle.getText().trim();
        String speciality = txtSpeciality.getText().trim();
        String workplaceId = txtWorkplaceId.getText().trim();
        String workplaceType = txtWorkplaceType.getText().trim();
        String employmentStatus = txtEmploymentStatus.getText().trim();
        LocalDate startDate = LocalDate.parse(txtStartDate.getText().trim());
        String gmcNumber = txtGmcNumber.getText().trim();

        return new Clinician(
                userId,
                firstName,
                lastName,
                email,
                phone,
                clinicianId,
                title,
                speciality,
                workplaceId,
                workplaceType,
                employmentStatus,
                startDate,
                gmcNumber
        );
    }
}
