package models;
public class GP extends Clinician {
    public GP() { }

    public GP(Clinician base) {
        super(base.getUserId(), base.getFirstName(), base.getLastName(),
              base.getEmail(), base.getPhone(),
              base.getClinicianId(), base.getTitle(), base.getSpeciality(),
              base.getWorkplaceId(), base.getWorkplaceType(),
              base.getEmploymentStatus(), base.getStartDate(),
              base.getGmcNumber());
    }

    // domain methods will call into controllers/services, kept simple for now
    public void createPrescription() { }
    public void createReferral() { }
    public void recordObservation() { }
}
