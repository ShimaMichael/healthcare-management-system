package models;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Clinician clinician;
    private Facility facility;
    private LocalDateTime startDateTime;
    private int durationMinutes;
    private String appointmentType;
    private String status;
    private String reasonForVisit;
    private String notes;
    private LocalDate createdDate;
    private LocalDate lastModified;

    public Appointment() { }

    public Appointment(String appointmentId,
                       Patient patient,
                       Clinician clinician,
                       Facility facility,
                       LocalDateTime startDateTime,
                       int durationMinutes,
                       String appointmentType,
                       String status,
                       String reasonForVisit,
                       String notes,
                       LocalDate createdDate,
                       LocalDate lastModified) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.clinician = clinician;
        this.facility = facility;
        this.startDateTime = startDateTime;
        this.durationMinutes = durationMinutes;
        this.appointmentType = appointmentType;
        this.status = status;
        this.reasonForVisit = reasonForVisit;
        this.notes = notes;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
    }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public Patient getPatient() { return patient; }
    public void setPatient(Patient patient) { this.patient = patient; }

    public Clinician getClinician() { return clinician; }
    public void setClinician(Clinician clinician) { this.clinician = clinician; }

    public Facility getFacility() { return facility; }
    public void setFacility(Facility facility) { this.facility = facility; }

    public LocalDateTime getStartDateTime() { return startDateTime; }
    public void setStartDateTime(LocalDateTime startDateTime) { this.startDateTime = startDateTime; }

    public int getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(int durationMinutes) { this.durationMinutes = durationMinutes; }

    public String getAppointmentType() { return appointmentType; }
    public void setAppointmentType(String appointmentType) { this.appointmentType = appointmentType; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getReasonForVisit() { return reasonForVisit; }
    public void setReasonForVisit(String reasonForVisit) { this.reasonForVisit = reasonForVisit; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public LocalDate getCreatedDate() { return createdDate; }
    public void setCreatedDate(LocalDate createdDate) { this.createdDate = createdDate; }

    public LocalDate getLastModified() { return lastModified; }
    public void setLastModified(LocalDate lastModified) { this.lastModified = lastModified; }
}
