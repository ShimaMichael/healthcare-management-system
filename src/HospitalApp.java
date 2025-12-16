import controllers.LoginController;
import gui.LoginFrame;
import models.Patient;
import models.Clinician;
import models.AdminStaff;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class HospitalApp {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // TODO: load patients, clinicians, admins from CSV/text here
            List<Patient> patients = new ArrayList<>();
            List<Clinician> clinicians = new ArrayList<>();
            List<AdminStaff> admins = new ArrayList<>();

            LoginFrame login = new LoginFrame();
            new LoginController(login, patients, clinicians, admins);
            login.setVisible(true);
        });
    }
}
