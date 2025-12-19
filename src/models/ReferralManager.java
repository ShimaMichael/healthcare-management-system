package models;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;

public class ReferralManager {

    private static ReferralManager instance;

    private final Deque<Referral> referralQueue;
    private final String auditFilePath;
    private final String emailOutboxFilePath;

    private ReferralManager() {
        this.referralQueue = new ArrayDeque<>();
        this.auditFilePath = "referral_audit.txt";
        this.emailOutboxFilePath = "referral_emails.txt";
    }

    public static synchronized ReferralManager getInstance() {
        if (instance == null) {
            instance = new ReferralManager();
        }
        return instance;
    }

    public void enqueueReferral(Referral referral) {
        if (referral == null) return;
        referralQueue.addLast(referral);
        appendAuditLine("ENQUEUE", referral);
    }

    public Referral dequeueReferral() {
        Referral r = referralQueue.pollFirst();
        if (r != null) {
            appendAuditLine("DEQUEUE", r);
        }
        return r;
    }

    public void processNextReferral() {
        Referral referral = dequeueReferral();
        if (referral == null) {
            return;
        }

        String emailText = buildEmailText(referral);
        writeEmailToFile(emailText);
        updatePatientRecord(referral);

        referral.setStatus("PROCESSED");
        referral.setLastUpdated(LocalDateTime.now().toLocalDate());
        appendAuditLine("PROCESSED", referral);
    }

    private String buildEmailText(Referral r) {
        StringBuilder sb = new StringBuilder();
        sb.append("Referral ID: ").append(r.getReferralId()).append(System.lineSeparator());
        sb.append("Patient: ");
        if (r.getPatientId() != null) {
            sb.append(r.getPatientId()); //.getFullName());
        }
        sb.append(System.lineSeparator());
        sb.append("Referring clinician: ");
        if (r.getReferringClinicianId() != null) {
            sb.append(r.getReferringClinicianId()); //.getFullName());
        }
        sb.append(System.lineSeparator());
        sb.append("Referred clinician: ");
        if (r.getReferredClinicianId() != null) {
            sb.append(r.getReferredClinicianId()); //).getFullName());
        }
        sb.append(System.lineSeparator());
        sb.append("Reason: ").append(r.getReferralReason()).append(System.lineSeparator());
        sb.append("Urgency: ").append(r.getUrgencyLevel()).append(System.lineSeparator());
        sb.append("Clinical summary: ").append(r.getClinicalSummary()).append(System.lineSeparator());
        sb.append("Requested investigation: ").append(r.getRequestedInvestigation()).append(System.lineSeparator());
        sb.append("Status: ").append(r.getStatus()).append(System.lineSeparator());
        sb.append("-----").append(System.lineSeparator());
        return sb.toString();
    }

    private void writeEmailToFile(String emailText) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(emailOutboxFilePath, true))) {
            writer.write("=== " + LocalDateTime.now() + " ===");
            writer.newLine();
            writer.write(emailText);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void appendAuditLine(String action, Referral referral) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(auditFilePath, true))) {
            writer.write(LocalDateTime.now() + " | " + action + " | " + referral.getReferralId());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void updatePatientRecord(Referral referral) {
        String patientId = referral.getPatientId();
        Patient patient = new Patient();
        if (patient == null) return;

        // assuming Patient has a PatientRecord field or getter
        // if not, adjust to however you store records
        PatientRecord record = patient.getPatientRecord();
        if (record != null) {
            record.addReferral(referral);
        }
    }
}
