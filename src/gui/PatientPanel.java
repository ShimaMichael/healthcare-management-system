package gui;

import models.Patient;

import javax.swing.*;
import java.awt.*;

public class PatientPanel extends JPanel {

    // Top info labels
    private final JLabel lblNameValue = new JLabel();
    private final JLabel lblNhsValue = new JLabel();
    private final JLabel lblDobValue = new JLabel();
    private final JLabel lblGenderValue = new JLabel();
    private final JLabel lblPostcodeValue = new JLabel();

    // Tables
    private final JTable tblAppointments = new JTable();
    private final JTable tblPrescriptions = new JTable();
    private final JTable tblReferrals = new JTable();

    private final JButton btnNewAppt    = new JButton("New");
    private final JButton btnEditAppt   = new JButton("Edit");
    private final JButton btnCancelAppt = new JButton("Cancel");

    // getters for controller
    public JButton getBtnNewAppt()    { return btnNewAppt; }
    public JButton getBtnEditAppt()   { return btnEditAppt; }
    public JButton getBtnCancelAppt() { return btnCancelAppt; }

    public PatientPanel() {
        setLayout(new BorderLayout(10, 10));
        add(buildHeaderPanel(), BorderLayout.NORTH);
        add(buildTabs(), BorderLayout.CENTER);
    }

    private JPanel buildHeaderPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 4, 10, 5));
        panel.setBorder(BorderFactory.createTitledBorder("My Details"));

        panel.add(new JLabel("Name:"));
        panel.add(lblNameValue);

        panel.add(new JLabel("NHS:"));
        panel.add(lblNhsValue);

        panel.add(new JLabel("DOB:"));
        panel.add(lblDobValue);

        panel.add(new JLabel("Gender:"));
        panel.add(lblGenderValue);

        panel.add(new JLabel("Postcode:"));
        panel.add(lblPostcodeValue);

        return panel;
    }

    private JTabbedPane buildTabs() {
        JTabbedPane tabs = new JTabbedPane();

        // Appointments tab: table + buttons for patients
        JPanel apptPanel = new JPanel(new BorderLayout());
        apptPanel.add(new JScrollPane(tblAppointments), BorderLayout.CENTER);

        JPanel apptButtons = new JPanel(new FlowLayout(FlowLayout.LEFT));
        apptButtons.add(btnNewAppt);
        apptButtons.add(btnEditAppt);
        apptButtons.add(btnCancelAppt);
        apptPanel.add(apptButtons, BorderLayout.SOUTH);

        tabs.addTab("Appointments", apptPanel);

        // Prescriptions: read‑only
        tabs.addTab("Prescriptions", new JScrollPane(tblPrescriptions));

        // Referrals: read‑only
        tabs.addTab("Referrals", new JScrollPane(tblReferrals));

        return tabs;
    }

    // ----- methods the controller will call -----

    public void setPatientInfo(Patient p) {
        lblNameValue.setText(p.getFullName());
        lblNhsValue.setText(p.getNhsNumber());
        lblDobValue.setText(p.getDateOfBirth().toString());
        lblGenderValue.setText(p.getGender());
        lblPostcodeValue.setText(p.getPostcode());
    }

    public JTable getAppointmentsTable() { return tblAppointments; }

    public JTable getPrescriptionsTable() { return tblPrescriptions; }

    public JTable getReferralsTable() { return tblReferrals; }
}
