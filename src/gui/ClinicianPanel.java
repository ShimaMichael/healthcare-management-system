package gui;
import javax.swing.*;
import java.awt.*;
import models.*;

public class ClinicianPanel extends JPanel {

    private final JLabel lblNameValue = new JLabel();
    private final JLabel lblIdValue = new JLabel();
    private final JLabel lblSpecialityValue = new JLabel();
    private final JLabel lblWorkplaceValue = new JLabel();

    private final JTable tblAppointments = new JTable();
    private final JTable tblPrescriptions = new JTable();
    private final JTable tblReferrals = new JTable();

    // buttons only where clinician is allowed to act
    private final JButton btnNewPrescription = new JButton("New Prescription");
    private final JButton btnDeletePrescription = new JButton("Delete");
    private final JButton btnNewReferral = new JButton("New Referral");
    private final JButton btnDeleteReferral = new JButton("Delete");

    public ClinicianPanel() {
        setLayout(new BorderLayout(10, 10));
        add(buildHeader(), BorderLayout.NORTH);
        add(buildTabs(), BorderLayout.CENTER);
    }

    private JPanel buildHeader() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("My Details"));

        panel.add(new JLabel("Name:"));
        panel.add(lblNameValue);
        panel.add(new JLabel("Clinician ID:"));
        panel.add(lblIdValue);

        panel.add(new JLabel("Speciality:"));
        panel.add(lblSpecialityValue);
        panel.add(new JLabel("Workplace:"));
        panel.add(lblWorkplaceValue);

        return panel;
    }

    private JTabbedPane buildTabs() {
        JTabbedPane tabs = new JTabbedPane();

        // Appointments: view only
        tabs.addTab("Appointments", new JScrollPane(tblAppointments));

        // Prescriptions: table + buttons
        JPanel prescPanel = new JPanel(new BorderLayout());
        prescPanel.add(new JScrollPane(tblPrescriptions), BorderLayout.CENTER);
        JPanel prescBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        prescBtns.add(btnNewPrescription);
        prescBtns.add(btnDeletePrescription);
        prescPanel.add(prescBtns, BorderLayout.SOUTH);
        tabs.addTab("Prescriptions", prescPanel);

        // Referrals: table + buttons
        JPanel refPanel = new JPanel(new BorderLayout());
        refPanel.add(new JScrollPane(tblReferrals), BorderLayout.CENTER);
        JPanel refBtns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        refBtns.add(btnNewReferral);
        refBtns.add(btnDeleteReferral);
        refPanel.add(refBtns, BorderLayout.SOUTH);
        tabs.addTab("Referrals", refPanel);

        return tabs;
    }

    public void setClinicianInfo(Clinician c, String workplaceName) {
        lblNameValue.setText(c.getFullName());
        lblIdValue.setText(c.getClinicianId());
        lblSpecialityValue.setText(c.getSpeciality());
        lblWorkplaceValue.setText(workplaceName);
    }

    public JTable getAppointmentsTable()   { return tblAppointments; }
    public JTable getPrescriptionsTable()  { return tblPrescriptions; }
    public JTable getReferralsTable()      { return tblReferrals; }

    public JButton getBtnNewPrescription() { return btnNewPrescription; }
    public JButton getBtnDeletePrescription() { return btnDeletePrescription; }
    public JButton getBtnNewReferral()     { return btnNewReferral; }
    public JButton getBtnDeleteReferral()  { return btnDeleteReferral; }
}

