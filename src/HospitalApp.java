import controllers.LoginController;
import gui.LoginFrame;
import models.*;
import services.*;

import javax.swing.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HospitalApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // ArrayList<Patient> patients;
            // ArrayList<Clinician> clinicians;
            // ArrayList<Facility> facilities;
            // ArrayList<Appointment>  appointments;
            // ArrayList<Prescription> prescriptions;
            // ArrayList<Referral> referrals;
            // ArrayList<AdminStaff> admins;
            
            // patients        = loader.loadPatients();
            // clinicians      = loader.loadClinicians();
            // facilities      = loader.loadFacilities();
            // appointments    = loader.loadAppointments();
            // prescriptions   = loader.loadPrescriptions();
            // referrals       = loader.loadReferrals();
            // admins          = loader.loadAdmins();

            
            DataLoader loader = new DataLoader();
            InAppData inAppData = new InAppData(
                loader.loadPatients(),
                loader.loadClinicians(),
                loader.loadFacilities(),
                loader.loadAppointments(),
                loader.loadPrescriptions(),
                loader.loadReferrals(),
                loader.loadAdmins()
            );

            PatientRecordBuilder builder = new PatientRecordBuilder(inAppData);
            inAppData.setRecords(builder.buildAll());
            LoginFrame login = new LoginFrame();
            new LoginController(login, inAppData);
            login.setVisible(true);
        });
    }
}
