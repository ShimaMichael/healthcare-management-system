package controllers;

import gui.LoginFrame;
import gui.MainFrame;
import gui.PatientPanel;
import gui.ClinicianPanel;
import gui.AppointmentPanel;
import gui.PrescriptionPanel;
import gui.ReferralPanel;
import models.*;


import javax.swing.*;
import java.util.List;

public class LoginController {

    private final LoginFrame loginView;
    private final List<Patient> patients;
    private final List<Clinician> clinicians;
    private final List<AdminStaff> admins;
    private final InAppData inAppData;

    public LoginController(LoginFrame loginView, InAppData inAppData) {
            this.inAppData = inAppData;
            this.loginView = loginView;
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
            JOptionPane.showMessageDialog(loginView, "User not found for given ID and role");
            return;
        }

        MainFrame mainFrame = new MainFrame();
        PatientPanel patientPanel = mainFrame.getPatientPanel();
        ClinicianPanel clinicianPanel = mainFrame.getClinicianPanel();
        AppointmentPanel appointmentPanel = mainFrame.getAppointmentPanel();
        PrescriptionPanel prescriptionPanel = mainFrame.getPrescriptionPanel();
        ReferralPanel referralPanel = mainFrame.getReferralPanel();

        PatientController pc = new PatientController(patientPanel, inAppData, currentUser instanceof Patient ? ((Patient) currentUser).getUserId() : null);
        ClinicianController cc = new ClinicianController(clinicianPanel);
        AppointmentController ac = new AppointmentController(appointmentPanel);
        PrescriptionController prc = new PrescriptionController(prescriptionPanel);
        ReferralController rc = new ReferralController(referralPanel);

        // optional: role‑based enable/disable
        if (currentUser instanceof Patient) {
            clinicianPanel.setEnabled(false);
            prescriptionPanel.getBtnAdd().setEnabled(false);
        } else if (currentUser instanceof Clinician) {
            // clinicians can handle prescriptions and referrals, maybe not delete patients
            //patientPanel.getBtnDelete().setEnabled(false);
        }

        loginView.dispose();
        mainFrame.setVisible(true);
    }
}
