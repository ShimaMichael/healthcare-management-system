package models;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class Appointment {
    private String appointmentId;
    private String patientId;
    private String clinicianId;
    private String facilityId;
    private LocalDate appointmentDate;
    private int durationMinutes;
    private String appointmentType;
    private String status;
    private String reasonForVisit;
    private String notes;
    private LocalDate createdDate;
    private LocalDate lastModified;

    public Appointment() { }

    public Appointment(String appointmentId,
                       String patientId,
                       String clinicianId,
                       String facilityId,
                       LocalDate appointmentDate,
                       int durationMinutes,
                       String appointmentType,
                       String status,
                       String reasonForVisit,
                       String notes,
                       LocalDate createdDate,
                       LocalDate lastModified) {
        this.appointmentId = appointmentId;
        this.patientId = patientId;
        this.clinicianId = clinicianId;
        this.facilityId = facilityId;
        this.appointmentDate = appointmentDate;
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

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }
    
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
