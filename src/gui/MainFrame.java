import javax.swing.*;

public class MainFrame extends JFrame {

    private PatientPanel patientPanel;
    private ClinicianPanel clinicianPanel;
    private AppointmentPanel appointmentPanel;
    private PrescriptionPanel prescriptionPanel;
    private ReferralPanel referralPanel;

    public MainFrame() {
        super("Clinic Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();

        patientPanel = new PatientPanel();
        clinicianPanel = new ClinicianPanel();
        appointmentPanel = new AppointmentPanel();
        prescriptionPanel = new PrescriptionPanel();
        referralPanel = new ReferralPanel();

        tabbedPane.addTab("Patients", patientPanel);
        tabbedPane.addTab("Clinicians", clinicianPanel);
        tabbedPane.addTab("Appointments", appointmentPanel);
        tabbedPane.addTab("Prescriptions", prescriptionPanel);
        tabbedPane.addTab("Referrals", referralPanel);

        add(tabbedPane);
    }

    public PatientPanel getPatientPanel() { return patientPanel; }
    public ClinicianPanel getClinicianPanel() { return clinicianPanel; }
    public AppointmentPanel getAppointmentPanel() { return appointmentPanel; }
    public PrescriptionPanel getPrescriptionPanel() { return prescriptionPanel; }
    public ReferralPanel getReferralPanel() { return referralPanel; }
}
