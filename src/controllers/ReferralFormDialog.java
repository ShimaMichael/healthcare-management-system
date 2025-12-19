package controllers;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import models.Referral;

public class ReferralFormDialog extends JDialog {

    private JTextField txtReferralId;
    private JTextField txtPatientId;
    private JTextField txtRefClinicianId;
    private JTextField txtReferredClinicianId;
    private JTextField txtRefFacilityId;
    private JTextField txtReferredFacilityId;
    private JTextField txtDate;
    private JTextField txtUrgency;
    private JTextField txtReason;
    private JTextField txtSummary;
    private JTextField txtInvestigation;
    private JTextField txtStatus;
    private JTextField txtNotes;

    private boolean confirmed;
    private Referral existing;

    public ReferralFormDialog() {
        this(null);
    }

    public ReferralFormDialog(Referral existing) {
        this.existing = existing;
        setModal(true);
        setTitle(existing == null ? "Add Referral" : "Edit Referral");
        setSize(550, 400);
        setLocationRelativeTo(null);
        buildForm();
        if (existing != null) populateFromExisting();
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        txtReferralId = new JTextField();
        txtPatientId = new JTextField();
        txtRefClinicianId = new JTextField();
        txtReferredClinicianId = new JTextField();
        txtRefFacilityId = new JTextField();
        txtReferredFacilityId = new JTextField();
        txtDate = new JTextField();
        txtUrgency = new JTextField();
        txtReason = new JTextField();
        txtSummary = new JTextField();
        txtInvestigation = new JTextField();
        txtStatus = new JTextField();
        txtNotes = new JTextField();

        panel.add(new JLabel("Referral ID:"));
        panel.add(txtReferralId);
        panel.add(new JLabel("Patient ID:"));
        panel.add(txtPatientId);
        panel.add(new JLabel("Referring clinician ID:"));
        panel.add(txtRefClinicianId);
        panel.add(new JLabel("Referred clinician ID:"));
        panel.add(txtReferredClinicianId);
        panel.add(new JLabel("Referring facility ID:"));
        panel.add(txtRefFacilityId);
        panel.add(new JLabel("Referred facility ID:"));
        panel.add(txtReferredFacilityId);
        panel.add(new JLabel("Date (yyyy-MM-dd):"));
        panel.add(txtDate);
        panel.add(new JLabel("Urgency:"));
        panel.add(txtUrgency);
        panel.add(new JLabel("Reason:"));
        panel.add(txtReason);
        panel.add(new JLabel("Clinical summary:"));
        panel.add(txtSummary);
        panel.add(new JLabel("Requested investigation:"));
        panel.add(txtInvestigation);
        panel.add(new JLabel("Status:"));
        panel.add(txtStatus);
        panel.add(new JLabel("Notes:"));
        panel.add(txtNotes);

        JPanel buttons = new JPanel();
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        buttons.add(btnOk);
        buttons.add(btnCancel);

        btnOk.addActionListener(e -> { confirmed = true; setVisible(false); });
        btnCancel.addActionListener(e -> { confirmed = false; setVisible(false); });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    private void populateFromExisting() {
        txtReferralId.setText(existing.getReferralId());
        txtPatientId.setText(existing.getPatientId() != null ? existing.getPatientId() : "");
        txtRefClinicianId.setText(existing.getReferringClinicianId() != null ? existing.getReferringClinicianId() : "");
        txtReferredClinicianId.setText(existing.getReferredClinicianId() != null ? existing.getReferredClinicianId() : "");
        txtRefFacilityId.setText(existing.getReferringFacilityId() != null ? existing.getReferringFacilityId() : "");
        txtReferredFacilityId.setText(existing.getReferredFacilityId() != null ? existing.getReferredFacilityId() : "");
        txtDate.setText(existing.getReferralDate() != null ? existing.getReferralDate().toString() : "");
        txtUrgency.setText(existing.getUrgencyLevel());
        txtReason.setText(existing.getReferralReason());
        txtSummary.setText(existing.getClinicalSummary());
        txtInvestigation.setText(existing.getRequestedInvestigation());
        txtStatus.setText(existing.getStatus());
        txtNotes.setText(existing.getNotes());
    }

    public boolean isConfirmed() { return confirmed; }

    public Referral getReferral() {
        String id = txtReferralId.getText().trim();
        String patientId = txtPatientId.getText().trim();
        String refClinicianId = txtRefClinicianId.getText().trim();
        String referredClinicianId = txtReferredClinicianId.getText().trim();
        String refFacilityId = txtRefFacilityId.getText().trim();
        String referredFacilityId = txtReferredFacilityId.getText().trim();
        LocalDate date = LocalDate.parse(txtDate.getText().trim());
        String urgency = txtUrgency.getText().trim();
        String reason = txtReason.getText().trim();
        String summary = txtSummary.getText().trim();
        String investigation = txtInvestigation.getText().trim();
        String status = txtStatus.getText().trim();
        String notes = txtNotes.getText().trim();

        Referral r = new Referral();
        r.setReferralId(id);
        r.setReferralDate(date);
        r.setUrgencyLevel(urgency);
        r.setReferralReason(reason);
        r.setClinicalSummary(summary);
        r.setRequestedInvestigation(investigation);
        r.setStatus(status);
        r.setNotes(notes);
        r.setCreatedDate(LocalDate.now());
        r.setLastUpdated(LocalDate.now());

        // Patient, clinicians, facilities resolved later from IDs if needed
        r.setPatientId(null);
        r.setReferringClinicianId(null);
        r.setReferredClinicianId(null);
        r.setReferringFacilityId(null);
        r.setReferredFacilityId(null);
        r.setLinkedAppointmentId(null);

        return r;
    }
}
