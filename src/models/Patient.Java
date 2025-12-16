import java.time.LocalDate;

public class Patient extends User {
    private String patientId;
    private int nhsNumber;
    private String gender;
    private String address;
    private String postcode;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private LocalDate registrationDate;
    private String gpSurgeryId;

    public Patient() { }

    public Patient(String userId,
                   String firstName,
                   String lastName,
                   LocalDate dateOfBirth,
                   String email,
                   String phone,
                   String patientId,
                   int nhsNumber,
                   String gender,
                   String address,
                   String postcode,
                   String emergencyContactName,
                   String emergencyContactPhone,
                   LocalDate registrationDate,
                   String gpSurgeryId) {
        super(userId, firstName, lastName, UserType.PATIENT, dateOfBirth, email, phone);
        this.patientId = patientId;
        this.nhsNumber = nhsNumber;
        this.gender = gender;
        this.address = address;
        this.postcode = postcode;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.registrationDate = registrationDate;
        this.gpSurgeryId = gpSurgeryId;
    }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public int getNhsNumber() { return nhsNumber; }
    public void setNhsNumber(int nhsNumber) { this.nhsNumber = nhsNumber; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }

    public LocalDate getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(LocalDate registrationDate) { this.registrationDate = registrationDate; }

    public String getGpSurgeryId() { return gpSurgeryId; }
    public void setGpSurgeryId(String gpSurgeryId) { this.gpSurgeryId = gpSurgeryId; }
}
