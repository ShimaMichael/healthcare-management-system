import controllers.LoginController;
import fileIO.DataLoader;
import gui.LoginFrame;
import models.*;

import fileIO.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class HospitalApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Patient> patients;
            ArrayList<Clinician> clinicians;
            ArrayList<Facility> facilities;
            ArrayList<Appointment>  appointments;
            ArrayList<Prescription> prescriptions;
            ArrayList<Referral> referrals;
            ArrayList<AdminStaff> admins;
            DataLoader loader = new DataLoader();

            try {
                patients        = loader.loadPatients();
                clinicians      = loader.loadClincians();
                //facilities      = loader.loadFacilities();
                //appointments    = loader.loadAppointments();
                //prescriptions   = loader.loadPrescriptions();
                referrals       = loader.loadReferrals();
                admins          = loader.loadAdmins();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }




            LoginFrame login = new LoginFrame();
            new LoginController(login, patients, clinicians, admins);
            login.setVisible(true);
        });
    }
}
