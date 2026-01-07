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
            ReferralManager.getInstance().init(inAppData);
            LoginFrame login = new LoginFrame();
            new LoginController(login, inAppData);
            login.setVisible(true);
        });
    }
}
