import java.time.LocalDate;

public class Clinician extends User {
    private String clinicianId;
    private String title;
    private String speciality;
    private String workplaceId;
    private String workplaceType;
    private String employmentStatus;
    private LocalDate startDate;
    private int gmcNumber;

    public Clinician() { }

    public Clinician(String userId,
                     String firstName,
                     String lastName,
                     LocalDate dateOfBirth,
                     String email,
                     String phone,
                     String clinicianId,
                     String title,
                     String speciality,
                     String workplaceId,
                     String workplaceType,
                     String employmentStatus,
                     LocalDate startDate,
                     int gmcNumber) {
        super(userId, firstName, lastName, UserType.CLINICIAN, dateOfBirth, email, phone);
        this.clinicianId = clinicianId;
        this.title = title;
        this.speciality = speciality;
        this.workplaceId = workplaceId;
        this.workplaceType = workplaceType;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.gmcNumber = gmcNumber;
    }

    public String getClinicianId() { return clinicianId; }
    public void setClinicianId(String clinicianId) { this.clinicianId = clinicianId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSpeciality() { return speciality; }
    public void setSpeciality(String speciality) { this.speciality = speciality; }

    public String getWorkplaceId() { return workplaceId; }
    public void setWorkplaceId(String workplaceId) { this.workplaceId = workplaceId; }

    public String getWorkplaceType() { return workplaceType; }
    public void setWorkplaceType(String workplaceType) { this.workplaceType = workplaceType; }

    public String getEmploymentStatus() { return employmentStatus; }
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public int getGmcNumber() { return gmcNumber; }
    public void setGmcNumber(int gmcNumber) { this.gmcNumber = gmcNumber; }
}
