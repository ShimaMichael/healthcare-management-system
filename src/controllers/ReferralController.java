package controllers;

import javax.swing.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import gui.ReferralPanel;
import models.Referral;
import models.ReferralManager;

public class ReferralController {

    private final ReferralPanel view;
    private final List<Referral> referrals;
    private final ListTableModel<Referral> tableModel;
    private final ReferralManager referralManager;

    public ReferralController(ReferralPanel view) {
        this.view = view;
        this.referrals = new ArrayList<>();
        this.referralManager = ReferralManager.getInstance();

        String[] columns = { "ID", "Patient", "From", "To", "Date", "Urgency", "Status" };

        this.tableModel = new ListTableModel<>(
                columns,
                referrals,
                (r, col) -> {
                    switch (col) {
                        case 0: return r.getReferralId();
                        case 1: return r.getPatientId() != null ? r.getPatientId() : "";
                        case 2: return r.getReferringClinicianId() != null ? r.getReferringClinicianId() : "";
                        case 3: return r.getReferredClinicianId() != null ? r.getReferredClinicianId() : "";
                        case 4: return r.getReferralDate();
                        case 5: return r.getUrgencyLevel();
                        case 6: return r.getStatus();
                        default: return "";
                    }
                },
                x -> {}
        );

        view.getTable().setModel(tableModel);
        addListeners();
    }

    private void addListeners() {
        view.getBtnAdd().addActionListener(e -> onAddReferral());
        view.getBtnEdit().addActionListener(e -> onEditReferral());
        view.getBtnDelete().addActionListener(e -> onDeleteReferral());
        view.getBtnProcessNext().addActionListener(e -> onProcessNextReferral());
    }

    private void onAddReferral() {
        ReferralFormDialog dialog = new ReferralFormDialog();
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Referral r = dialog.getReferral();
            tableModel.addRow(r);
            referralManager.enqueueReferral(r);
            // TODO: append to referrals file
        }
    }

    private void onEditReferral() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a referral first");
            return;
        }
        Referral existing = tableModel.getRow(row);
        ReferralFormDialog dialog = new ReferralFormDialog(existing);
        dialog.setVisible(true);
        if (dialog.isConfirmed()) {
            Referral updated = dialog.getReferral();
            tableModel.updateRow(row, updated);
            // might update queue logic if needed
            // TODO: rewrite referrals file
        }
    }

    private void onDeleteReferral() {
        int row = view.getTable().getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(view, "Select a referral first");
            return;
        }
        tableModel.removeRow(row);
        // queue removal could be added if required
        // TODO: update referrals file
    }

    private void onProcessNextReferral() {
        referralManager.processNextReferral();
        JOptionPane.showMessageDialog(view,
                "Next referral processed.\nSee referral_emails.txt and referral_audit.txt for output.");
        // optional: update table to reflect status changes if you reload from file
    }
}
