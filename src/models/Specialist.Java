package models;

public class Specialist extends Clinician {
    public Specialist() { }

    public Specialist(Clinician base) {
        super(base.getUserId(), base.getFirstName(), base.getLastName(),
              base.getDateOfBirth(), base.getEmail(), base.getPhone(),
              base.getClinicianId(), base.getTitle(), base.getSpeciality(),
              base.getWorkplaceId(), base.getWorkplaceType(),
              base.getEmploymentStatus(), base.getStartDate(),
              base.getGmcNumber());
    }

    public void viewReferral() { }
    public void updatePatientRecord() { }
}

