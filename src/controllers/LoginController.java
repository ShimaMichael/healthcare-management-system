package controllers;

import gui.*;
import models.*;

import javax.swing.*;
import java.util.List;

public class LoginController {

    private final LoginFrame loginView;
    private final InAppData inAppData;
    private final List<Patient> patients;
    private final List<Clinician> clinicians;
    private final List<AdminStaff> admins;

    public LoginController(LoginFrame loginView, InAppData inAppData) {
        this.loginView = loginView;
        this.inAppData = inAppData;
        this.patients = inAppData.getPatients();
        this.clinicians = inAppData.getClinicians();
        this.admins = inAppData.getAdminStaff();
        addListeners();
    }

    private void addListeners() {
        loginView.getBtnLogin().addActionListener(e -> onLogin());
    }

    private void onLogin() {
        String userId = loginView.getTxtUserId().getText().trim();
        String role = (String) loginView.getCmbRole().getSelectedItem();

        Object currentUser = null;

        if ("Patient".equals(role)) {
            currentUser = patients.stream()
                    .filter(p -> p.getUserId().equals(userId))
                    .findFirst().orElse(null);
        } else if ("Clinician".equals(role)) {
            currentUser = clinicians.stream()
                    .filter(c -> c.getUserId().equals(userId))
                    .findFirst().orElse(null);
        } else if ("AdminStaff".equals(role)) {
            currentUser = admins.stream()
                    .filter(a -> a.getUserId().equals(userId))
                    .findFirst().orElse(null);
        }

        if (currentUser == null) {
            JOptionPane.showMessageDialog(loginView,
                    "User not found for given ID and role");
            return;
        }

        // Build main window
        MainFrame mainFrame = new MainFrame();

        PatientPanel patientPanel = mainFrame.getPatientPanel();
        ClinicianPanel clinicianPanel = mainFrame.getClinicianPanel();
        AppointmentPanel appointmentPanel = mainFrame.getAppointmentPanel();
        PrescriptionPanel prescriptionPanel = mainFrame.getPrescriptionPanel();
        ReferralPanel referralPanel = mainFrame.getReferralPanel();

        // -------- PATIENT --------
        if (currentUser instanceof Patient patient) {

            mainFrame.setContentPane(mainFrame.buildPatientContent());
            mainFrame.getRoleLabel().setText("Logged in as: Patient");

            new PatientController(patientPanel, inAppData, patient.getUserId());

        // -------- CLINICIAN --------
        } else if (currentUser instanceof Clinician clinician) {

            mainFrame.setContentPane(mainFrame.buildStaffContent());
            mainFrame.getRoleLabel().setText("Logged in as: Clinician");

            new PatientController(patientPanel, inAppData, null);
            new ClinicianController(clinicianPanel, inAppData, clinician.getUserId());
            new AppointmentController(appointmentPanel);
            new PrescriptionController(prescriptionPanel);
            new ReferralController(referralPanel);

        // -------- ADMIN STAFF --------
        } else if (currentUser instanceof AdminStaff admin) {

            mainFrame.setContentPane(mainFrame.buildStaffContent());
            mainFrame.getRoleLabel().setText("Logged in as: Admin");

            new PatientAdminController(patientAdminPanel, inAppData);
            new AdminClinicianController(clinicianAdminPanel, inAppData);
            new AppointmentAdminController(appointmentPanel, inAppData);
            new FacilityAdminController(facilityPanel, inAppData);
        }

        mainFrame.getLogoutButton().addActionListener(e -> {
            mainFrame.dispose();
            loginView.setVisible(true);
            loginView.toFront();
        });

        loginView.setVisible(false);
        mainFrame.validate();  
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
}