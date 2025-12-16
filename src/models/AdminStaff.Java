import java.time.LocalDate;

public class AdminStaff extends User {
    private String staffId;
    private String role;
    private String department;
    private String facilityId;
    private String employmentStatus;
    private LocalDate startDate;
    private String lineManager;
    private String accessLevel;

    public AdminStaff() { }

    public AdminStaff(String userId,
                      String firstName,
                      String lastName,
                      LocalDate dateOfBirth,
                      String email,
                      String phone,
                      String staffId,
                      String role,
                      String department,
                      String facilityId,
                      String employmentStatus,
                      LocalDate startDate,
                      String lineManager,
                      String accessLevel) {
        super(userId, firstName, lastName, UserType.ADMIN, dateOfBirth, email, phone);
        this.staffId = staffId;
        this.role = role;
        this.department = department;
        this.facilityId = facilityId;
        this.employmentStatus = employmentStatus;
        this.startDate = startDate;
        this.lineManager = lineManager;
        this.accessLevel = accessLevel;
    }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public String getEmploymentStatus() { return employmentStatus; }
    public void setEmploymentStatus(String employmentStatus) { this.employmentStatus = employmentStatus; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public String getLineManager() { return lineManager; }
    public void setLineManager(String lineManager) { this.lineManager = lineManager; }

    public String getAccessLevel() { return accessLevel; }
    public void setAccessLevel(String accessLevel) { this.accessLevel = accessLevel; }
}
