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

            // configure layout: only PatientPanel in center
            mainFrame.setContentPane(mainFrame.buildPatientContent());
            mainFrame.getRoleLabel().setText("Logged in as: Patient");

            // patient-specific dashboard controller (using patientId)
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

            new PatientController(patientPanel, inAppData, null);
            new ClinicianController(clinicianPanel, inAppData, null);
            new AppointmentController(appointmentPanel);
            new PrescriptionController(prescriptionPanel);
            new ReferralController(referralPanel);
        }

        // wire logout for all roles: close main window, show login again
        mainFrame.getLogoutButton().addActionListener(e -> {
            mainFrame.dispose();
            loginView.setVisible(true);
            loginView.toFront();
        });

        loginView.setVisible(false);
        mainFrame.validate();   // ensure layout recalculated after setContentPane
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
}

// package controllers;

// import gui.LoginFrame;
// import gui.MainFrame;
// import gui.PatientPanel;
// import gui.ClinicianPanel;
// import gui.AppointmentPanel;
// import gui.PrescriptionPanel;
// import gui.ReferralPanel;
// import models.*;


// import javax.swing.*;
// import java.util.List;

// public class LoginController {

//     private final LoginFrame loginView;
//     private final List<Patient> patients;
//     private final List<Clinician> clinicians;
//     private final List<AdminStaff> admins;
//     private final InAppData inAppData;

//     public LoginController(LoginFrame loginView, InAppData inAppData) {
//             this.inAppData = inAppData;
//             this.loginView = loginView;
//             this.patients = inAppData.getPatients();
//             this.clinicians = inAppData.getClinicians();
//             this.admins = inAppData.getAdminStaff();
//             addListeners();
//     }

//     private void addListeners() {
//         loginView.getBtnLogin().addActionListener(e -> onLogin());
//     }

//     private void onLogin() {
//         String userId = loginView.getTxtUserId().getText().trim();
//         String role = (String) loginView.getCmbRole().getSelectedItem();

//         Object currentUser = null;

//         if ("Patient".equals(role)) {
//             currentUser = patients.stream()
//                     .filter(p -> p.getUserId().equals(userId))
//                     .findFirst().orElse(null);
//         } else if ("Clinician".equals(role)) {
//             currentUser = clinicians.stream()
//                     .filter(c -> c.getUserId().equals(userId))
//                     .findFirst().orElse(null);
//         } else if ("AdminStaff".equals(role)) {
//             currentUser = admins.stream()
//                     .filter(a -> a.getUserId().equals(userId))
//                     .findFirst().orElse(null);
//         }

//         if (currentUser == null) {
//             JOptionPane.showMessageDialog(loginView, "User not found for given ID and role");
//             return;
//         }

//         MainFrame mainFrame = new MainFrame();
//         JTabbedPane tabs = mainFrame.getTabbedPane();

//         PatientPanel patientPanel = mainFrame.getPatientPanel();
//         ClinicianPanel clinicianPanel = mainFrame.getClinicianPanel();
//         AppointmentPanel appointmentPanel = mainFrame.getAppointmentPanel();
//         PrescriptionPanel prescriptionPanel = mainFrame.getPrescriptionPanel();
//         ReferralPanel referralPanel = mainFrame.getReferralPanel();

//         if (currentUser instanceof Patient patient) {
//             // PATIENT VIEW: only patient dashboard tab
//             tabs.addTab("My Dashboard", patientPanel);

//             new PatientController(patientPanel, inAppData, patient.getUserId());
//             // no clinician/admin controllers needed here

//         } else {
//             // STAFF VIEW: full admin tabs
//             tabs.addTab("Patients", patientPanel);
//             tabs.addTab("Clinicians", clinicianPanel);
//             tabs.addTab("Appointments", appointmentPanel);
//             tabs.addTab("Prescriptions", prescriptionPanel);
//             tabs.addTab("Referrals", referralPanel);

//             new PatientController(patientPanel, inAppData, null);
//             new ClinicianController(clinicianPanel);
//             new AppointmentController(appointmentPanel);
//             new PrescriptionController(prescriptionPanel);
//             new ReferralController(referralPanel);
//         }

//         // wire logout
//         mainFrame.getLogoutButton().addActionListener(e -> {
//             mainFrame.dispose();
//             loginView.setVisible(true);
//         });

//         loginView.setVisible(false);
//         mainFrame.setVisible(true);
//     }

// }
