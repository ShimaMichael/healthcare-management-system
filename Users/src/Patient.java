public class Patient extends User{
    private String gender;
    private String address;
    private String emergencyContactName;
    private String emergencyContactNumber;
    private String gpSurgeryId;
    private String postCode;
    private int nhsNumber;


    public Patient(String patientId, int nhsNumber, String gender, String dob,
                   String address, String email, String phoneNumber, String postCode,
                   String emergencyContactName, String emergencyContactNumber,
                   String gpSurgeryId, String firstName, String lastName)
    {
        super(patientId, firstName, lastName, dob, email, phoneNumber);
        this.gender = gender;
        this.address = address;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactNumber = emergencyContactNumber;
        this.gpSurgeryId = gpSurgeryId;
        this.postCode = postCode;
        this.nhsNumber = nhsNumber;

    }
}
