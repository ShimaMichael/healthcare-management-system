package gui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final JButton logoutButton = new JButton("Logout");
    private final JLabel roleLabel    = new JLabel();   // "Logged in as: X"

    private final JTabbedPane tabbedPane = new JTabbedPane();

    private final PatientPanel patientPanel   = new PatientPanel();
    private final ClinicianPanel clinicianPanel = new ClinicianPanel();
    private final AppointmentPanel appointmentPanel = new AppointmentPanel();
    private final PrescriptionPanel prescriptionPanel = new PrescriptionPanel();
    private final ReferralPanel referralPanel = new ReferralPanel();

    public MainFrame() {
        super("Clinic Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        setContentPane(buildStaffContent()); // default; LoginController will replace
    }

    // ---------- header & layouts ----------

    private JPanel buildHeaderBar() {
        JPanel topBar = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Clinic Management System");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JPanel right = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        right.add(roleLabel);
        right.add(logoutButton);

        topBar.add(titleLabel, BorderLayout.WEST);
        topBar.add(right, BorderLayout.EAST);

        return topBar;
    }

    /** Layout for staff (tabs: Patients, Clinicians, ...) */
    public JPanel buildStaffContent() {
        JPanel content = new JPanel(new BorderLayout());
        content.add(buildHeaderBar(), BorderLayout.NORTH);
        content.add(clinicianPanel, BorderLayout.CENTER);
        return content;
    }

    /** Layout for patients (only PatientPanel in center) */
    public JPanel buildPatientContent() {
        JPanel content = new JPanel(new BorderLayout());
        content.add(buildHeaderBar(), BorderLayout.NORTH);
        content.add(patientPanel, BorderLayout.CENTER);
        return content;
    }

    public JPanel buildAdminContent() {
        JPanel content = new JPanel(new BorderLayout());
        content.add(buildHeaderBar(), BorderLayout.NORTH);

        tabbedPane.removeAll();
        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Clinicians", clinicianPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);
        tabbedPane.addTab("Prescriptions", prescriptionPanel);
        tabbedPane.addTab("Referrals", referralPanel);
        content.add(tabbedPane, BorderLayout.CENTER);
        return content;
    }

    // ---------- getters used by controllers ----------

    public JButton getLogoutButton() { return logoutButton; }
    public JLabel  getRoleLabel()    { return roleLabel; }

    public PatientPanel getPatientPanel() { return patientPanel; }
    public ClinicianPanel getClinicianPanel() { return clinicianPanel; }
    public AppointmentPanel getAppointmentPanel() { return appointmentPanel; }
    public PrescriptionPanel getPrescriptionPanel() { return prescriptionPanel; }
    public ReferralPanel getReferralPanel() { return referralPanel; }

    public JTabbedPane getTabbedPane() { return tabbedPane; }
}

// package gui;
// import javax.swing.*;
// import java.awt.*;


// public class MainFrame extends JFrame {

//     private final JTabbedPane tabbedPane;
//     private final JButton logoutButton;

//     private PatientPanel patientPanel;
//     private ClinicianPanel clinicianPanel;
//     private AppointmentPanel appointmentPanel;
//     private PrescriptionPanel prescriptionPanel;
//     private ReferralPanel referralPanel;

//     public MainFrame() {
//         super("Clinic Management System");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(1000, 700);
//         setLocationRelativeTo(null);

//         tabbedPane = new JTabbedPane();

//         // just create instances; don't necessarily add all as tabs
//         patientPanel = new PatientPanel();
//         clinicianPanel = new ClinicianPanel();
//         appointmentPanel = new AppointmentPanel();
//         prescriptionPanel = new PrescriptionPanel();
//         referralPanel = new ReferralPanel();

//         // header with logout
//         JPanel topBar = new JPanel(new BorderLayout());
//         JLabel titleLabel = new JLabel("Clinic Management System");
//         titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

//         logoutButton = new JButton("Logout");
//         topBar.add(titleLabel, BorderLayout.WEST);
//         topBar.add(logoutButton, BorderLayout.EAST);

//         JPanel content = new JPanel(new BorderLayout());
//         content.add(topBar, BorderLayout.NORTH);
//         content.add(tabbedPane, BorderLayout.CENTER);

//         setContentPane(content);
//     }

//     public JTabbedPane getTabbedPane() { return tabbedPane; }
//     public JButton getLogoutButton() { return logoutButton; }

//     public PatientPanel getPatientPanel() { return patientPanel; }
//     public ClinicianPanel getClinicianPanel() { return clinicianPanel; }
//     public AppointmentPanel getAppointmentPanel() { return appointmentPanel; }
//     public PrescriptionPanel getPrescriptionPanel() { return prescriptionPanel; }
//     public ReferralPanel getReferralPanel() { return referralPanel; }
// }


// /*
// public class MainFrame extends JFrame {

//     private PatientPanel patientPanel;
//     private ClinicianPanel clinicianPanel;
//     private AppointmentPanel appointmentPanel;
//     private PrescriptionPanel prescriptionPanel;
//     private ReferralPanel referralPanel;

//     public MainFrame() {
//         super("Clinic Management System");
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setSize(1000, 700);
//         setLocationRelativeTo(null);

//         JTabbedPane tabbedPane = new JTabbedPane();

//         patientPanel = new PatientPanel();
//         clinicianPanel = new ClinicianPanel();
//         appointmentPanel = new AppointmentPanel();
//         prescriptionPanel = new PrescriptionPanel();
//         referralPanel = new ReferralPanel();

//         tabbedPane.addTab("Patients", patientPanel);
//         tabbedPane.addTab("Clinicians", clinicianPanel);
//         tabbedPane.addTab("Appointments", appointmentPanel);
//         tabbedPane.addTab("Prescriptions", prescriptionPanel);
//         tabbedPane.addTab("Referrals", referralPanel);

//         add(tabbedPane);
//     }

//     public PatientPanel getPatientPanel() { return patientPanel; }
//     public ClinicianPanel getClinicianPanel() { return clinicianPanel; }
//     public AppointmentPanel getAppointmentPanel() { return appointmentPanel; }
//     public PrescriptionPanel getPrescriptionPanel() { return prescriptionPanel; }
//     public ReferralPanel getReferralPanel() { return referralPanel; }
// }
//     */
