import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class AppointmentFormDialog extends JDialog {

    private JTextField txtAppointmentId;
    private JTextField txtPatientId;
    private JTextField txtClinicianId;
    private JTextField txtFacilityId;
    private JTextField txtDate;      // yyyy-MM-dd
    private JTextField txtTime;      // HH:mm
    private JTextField txtDuration;  // minutes
    private JTextField txtType;
    private JTextField txtStatus;
    private JTextField txtReason;
    private JTextField txtNotes;

    private boolean confirmed;
    private Appointment existing;

    public AppointmentFormDialog() {
        this(null);
    }

    public AppointmentFormDialog(Appointment existing) {
        this.existing = existing;
        setModal(true);
        setTitle(existing == null ? "Add Appointment" : "Edit Appointment");
        setSize(450, 400);
        setLocationRelativeTo(null);
        buildForm();
        if (existing != null) populateFromExisting();
    }

    private void buildForm() {
        JPanel panel = new JPanel(new GridLayout(0, 2, 5, 5));

        txtAppointmentId = new JTextField();
        txtPatientId = new JTextField();
        txtClinicianId = new JTextField();
        txtFacilityId = new JTextField();
        txtDate = new JTextField();
        txtTime = new JTextField();
        txtDuration = new JTextField();
        txtType = new JTextField();
        txtStatus = new JTextField();
        txtReason = new JTextField();
        txtNotes = new JTextField();

        panel.add(new JLabel("Appointment ID:"));
        panel.add(txtAppointmentId);
        panel.add(new JLabel("Patient ID:"));
        panel.add(txtPatientId);
        panel.add(new JLabel("Clinician ID:"));
        panel.add(txtClinicianId);
        panel.add(new JLabel("Facility ID:"));
        panel.add(txtFacilityId);
        panel.add(new JLabel("Date (yyyy-MM-dd):"));
        panel.add(txtDate);
        panel.add(new JLabel("Time (HH:mm):"));
        panel.add(txtTime);
        panel.add(new JLabel("Duration (minutes):"));
        panel.add(txtDuration);
        panel.add(new JLabel("Type:"));
        panel.add(txtType);
        panel.add(new JLabel("Status:"));
        panel.add(txtStatus);
        panel.add(new JLabel("Reason:"));
        panel.add(txtReason);
        panel.add(new JLabel("Notes:"));
        panel.add(txtNotes);

        JPanel buttons = new JPanel();
        JButton btnOk = new JButton("OK");
        JButton btnCancel = new JButton("Cancel");
        buttons.add(btnOk);
        buttons.add(btnCancel);

        btnOk.addActionListener(e -> {
            confirmed = true;
            setVisible(false);
        });
        btnCancel.addActionListener(e -> {
            confirmed = false;
            setVisible(false);
        });

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(new JScrollPane(panel), BorderLayout.CENTER);
        getContentPane().add(buttons, BorderLayout.SOUTH);
    }

    private void populateFromExisting() {
        txtAppointmentId.setText(existing.getAppointmentId());
        txtPatientId.setText(existing.getPatient() != null ? existing.getPatient().getPatientId() : "");
        txtClinicianId.setText(existing.getClinician() != null ? existing.getClinician().getClinicianId() : "");
        txtFacilityId.setText(existing.getFacility() != null ? existing.getFacility().getFacilityId() : "");
        if (existing.getStartDateTime() != null) {
            txtDate.setText(existing.getStartDateTime().toLocalDate().toString());
            txtTime.setText(existing.getStartDateTime().toLocalTime().toString());
        }
        txtDuration.setText(String.valueOf(existing.getDurationMinutes()));
        txtType.setText(existing.getAppointmentType());
        txtStatus.setText(existing.getStatus());
        txtReason.setText(existing.getReasonForVisit());
        txtNotes.setText(existing.getNotes());
    }

    public boolean isConfirmed() { return confirmed; }

    public Appointment getAppointment() {
        String id = txtAppointmentId.getText().trim();
        String patientId = txtPatientId.getText().trim();
        String clinicianId = txtClinicianId.getText().trim();
        String facilityId = txtFacilityId.getText().trim();

        LocalDate date = LocalDate.parse(txtDate.getText().trim());
        LocalTime time = LocalTime.parse(txtTime.getText().trim());
        LocalDateTime start = LocalDateTime.of(date, time);

        int duration = Integer.parseInt(txtDuration.getText().trim());
        String type = txtType.getText().trim();
        String status = txtStatus.getText().trim();
        String reason = txtReason.getText().trim();
        String notes = txtNotes.getText().trim();

        Appointment a = new Appointment();
        a.setAppointmentId(id);
        a.setStartDateTime(start);
        a.setDurationMinutes(duration);
        a.setAppointmentType(type);
        a.setStatus(status);
        a.setReasonForVisit(reason);
        a.setNotes(notes);
        a.setCreatedDate(LocalDate.now());
        a.setLastModified(LocalDate.now());

        // For now, leave patient/clinician/facility null; the controller
        // could later resolve IDs to objects via repositories/services.
        a.setPatient(null);
        a.setClinician(null);
        a.setFacility(null);

        return a;
    }
}
