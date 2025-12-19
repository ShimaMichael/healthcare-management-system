package fileIO;

import models.*;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.List;

public class DataLoader {

    private final String patientFilePath = "data/patients.csv";
    private final String cliniciansFilePath = "data/clinicians.csv";
    private final String facilityFilePath = "data/facilities.csv";
    private final String appointmentFilePath = "data/appointments.csv";
    private final String prescriptionFilePath = "data/prescriptions.csv";
    private final String referralFilePath = "data/referrals.csv";
    private final String staffFilePath = "data/staff.csv";


    public ArrayList<Referral> loadReferrals() {
        ArrayList<Referral> referrals = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(this.referralFilePath))) {
            String line;
            br.readLine(); 

            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] f = line.split(",");

                String referralId               = f[0].trim();
                String patientId                = f[1].trim();
                String referringClinicianId     = f[2].trim();
                String referredClinicianId      = f[3].trim();
                String referringFacilityId      = f[4].trim();
                String referredFacilityId       = f[5].trim();
                LocalDate referralDate          = LocalDate.parse(f[6].trim());
                String urgencyLevel             = f[7].trim();
                String referralReason           = f[8].trim();
                String clinicalSummary          = f[9].trim();
                String requestedInvestigations  = f[10].trim();
                String status                   = f[11].trim();
                String appointmentId            = f[12].trim().isEmpty() ? null : f[12].trim();
                String notes                    = f[13].trim();
                LocalDate createdDate           = LocalDate.parse(f[14].trim());
                LocalDate lastUpdated           = LocalDate.parse(f[15].trim());

                System.out.println(referralId + "," + patientId + "," + referringClinicianId + "," + referredClinicianId +
                        "," + referringFacilityId + "," + referredFacilityId + "," + referralDate + "," + urgencyLevel +
                        "," + referralReason + "," + clinicalSummary + "," + requestedInvestigations + "," + status +
                        "," + appointmentId + "," + notes + "," + createdDate + "," + lastUpdated);
                Referral r = new Referral(
                        referralId,
                        patientId,
                        referringClinicianId,
                        referredClinicianId,
                        referringFacilityId,
                        referredFacilityId,
                        referralDate,
                        urgencyLevel,
                        referralReason,
                        clinicalSummary,
                        requestedInvestigations,
                        status,
                        appointmentId,
                        notes,
                        createdDate,
                        lastUpdated
                );
                referrals.add(r);
            }
        } catch (IOException e) {
            System.out.println("Error loading referrals: " + e.getMessage());
        }

        return referrals;
    }


    public ArrayList<Prescription> loadPrescriptions() throws FileNotFoundException {
        ArrayList<Prescription> prescriptions = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.prescriptionFilePath))){
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] f = line.split(",");

                String prescriptionId  = f[0].trim();
                String patientId       = f[1].trim();
                String clinicianId     = f[2].trim();
                String appointmentId   = f[3].trim();
                LocalDate prescriptionDate = LocalDate.parse(f[4].trim());
                String medicationName  = f[5].trim();
                String dosage          = f[6].trim();
                String frequency       = f[7].trim();
                int durationDays       = Integer.parseInt(f[8].trim());
                String quantity        = f[9].trim();
                String instructions    = f[10].trim();
                String pharmacyName    = f[11].trim();
                String status          = f[12].trim();
                LocalDate issueDate    = LocalDate.parse(f[13].trim());
                LocalDate collectionDate = f[14].trim().isEmpty()
                        ? null
                        : LocalDate.parse(f[14].trim());

                Prescription p = new Prescription(
                        prescriptionId, patientId, clinicianId, appointmentId,
                        prescriptionDate, medicationName, dosage, frequency,
                        durationDays, quantity, instructions, pharmacyName,
                        status, issueDate, collectionDate
                );
                prescriptions.add(p);

            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return prescriptions;
    }

    public ArrayList<AdminStaff> loadAdmins() throws FileNotFoundException {
        ArrayList<AdminStaff> staffs = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.staffFilePath))){
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] f = line.split(",");

                String staffId         = f[0].trim();  // staff_id
                String firstName       = f[1].trim();
                String lastName        = f[2].trim();
                String role            = f[3].trim();
                String department      = f[4].trim();
                String facilityId      = f[5].trim();
                String phone           = f[6].trim();
                String email           = f[7].trim();
                String employmentStatus= f[8].trim();
                LocalDate startDate    = LocalDate.parse(f[9].trim());
                String lineManager     = f[10].trim();
                String accessLevel     = f[11].trim();

                AdminStaff p = new AdminStaff(
                        staffId,
                        firstName,
                        lastName,
                        email,
                        phone,
                        staffId,
                        role,
                        department,
                        facilityId,
                        employmentStatus,
                        startDate,
                        lineManager,
                        accessLevel
                );
                staffs.add(p);

            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return staffs;
    }

    public ArrayList<Appointment> loadAppointments() throws FileNotFoundException {
        ArrayList<Appointment> appointments = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.appointmentFilePath))){
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] f = line.split(",");
                for  (int i = 0; i < f.length; i++){
                    System.out.println(i + " -> " + f[i]);
                }

                Appointment p = getAppointment(f);
                appointments.add(p);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return appointments;
    }

    private static Appointment getAppointment(String[] f) {
        String appointmentId         = f[0].trim();
        String patientId             = f[1].trim();
        String clinicianId           = f[2].trim();
        String facilityId            = f[3].trim();
        LocalDate appointmentDate    = LocalDate.parse(f[4].trim());
        int durationMinutes          = Integer.parseInt(f[5].trim());
        String appointmentType       = f[6].trim();
        String status                = f[7].trim();
        String reasonForVisit        = f[8].trim();
        String notes                 = f[9].trim();
        LocalDate createdDate        = LocalDate.parse(f[10].trim());
        LocalDate lastModified       = LocalDate.parse(f[11].trim());


        Appointment p = new Appointment(
                appointmentId, patientId, clinicianId, facilityId, appointmentDate,
                durationMinutes, appointmentType, status, reasonForVisit, notes,
                createdDate, lastModified
        );
        return p;
    }

    public ArrayList<Facility> loadFacilities() throws FileNotFoundException {
        ArrayList<Facility> facilities= new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.facilityFilePath))){
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] f = line.split(",");

                String facilityId            = f[0].trim();
                String facilityName          = f[1].trim();
                String facilityType          = f[2].trim();
                String address               = (f[3] + "," + f[4]).replace("\"", "").trim();
                String postcode              = f[5].trim();
                String email                 = f[6].trim();
                String phone                 = f[7].trim();
                String openingHours          = (f[8] + "," + f[9]).replace("\"", "").trim();
                String managerName           = f[10].trim();
                int capacity                 = Integer.parseInt(f[11].trim());
                String[] specialitiesOffered  = f[12].trim().split("\\|");

                List<String> specialityList =  new ArrayList<>();
                for (String s : specialitiesOffered){
                    if (!s.isBlank()) {
                        specialityList.add(s.trim());
                    }
                }

                System.out.println(facilityId + "\n" + facilityName + "\n"
                        +  facilityType + "\n" + address + "\n" + postcode
                        + "\n" + phone + "\n" + email + "\n" + openingHours
                        + "\n" + managerName + "\n" + capacity + "\n" + specialityList);

                Facility p = new Facility(
                        facilityId, facilityName, facilityType, address, postcode,
                        phone,
                        email, openingHours, managerName, capacity, specialityList
                );
                facilities.add(p);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return facilities;
    }

    public ArrayList<Clinician> loadClincians() throws FileNotFoundException {
        ArrayList<Clinician> clincians = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.cliniciansFilePath))){
            String line;
            br.readLine();

            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] f = line.split(",");

                // adjust indices to your CSV column order
                String userId                = f[0].trim();
                String firstName             = f[1].trim();
                String lastName              = f[2].trim();
                String title                 = f[3].trim();
                String specialty             = f[4].trim();
                String gmcNumber             = f[5].trim();
                String phone                 = f[6].trim();
                String email                 = f[7].trim();
                String workplaceId           = f[8].trim();
                String workplaceType         = f[9].trim();
                String employmentStatus      = f[10].trim();
                LocalDate startDate          = LocalDate.parse(f[11].trim());

                Clinician p = new Clinician(
                        userId, firstName, lastName, email, phone, userId,
                        title, specialty, workplaceId, workplaceType,
                        employmentStatus, startDate, gmcNumber
                );
                clincians.add(p);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return clincians;
    }

    public ArrayList<Patient> loadPatients() throws FileNotFoundException {
        ArrayList<Patient> patients = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.patientFilePath))){
            String line;
            br.readLine();
            System.out.println("PatientsFileIO loadFile");

            while ((line = br.readLine()) != null){
                if (line.trim().isEmpty()) continue;
                String[] f = line.split(",");

                // adjust indices to your CSV column order
                String userId                = f[0].trim();
                String firstName             = f[1].trim();
                String lastName              = f[2].trim();
                LocalDate dob                = LocalDate.parse(f[3].trim());
                String nhs                   = f[4].trim();
                String gender                = f[5].trim();
                String phone                 = f[6].trim();
                String email                 = f[7].trim();
                String address               = (f[8] + "," + f[9]).replace("\"", "").trim();
                String postcode              = f[10].trim();
                String emergencyContactName  = f[11].trim();
                String emergencyContactPhone = f[12].trim();
                LocalDate registrationDate   = LocalDate.parse(f[13].trim());
                String gpSurgeryId           = f[14].trim();

                Patient p = new Patient(
                        userId, firstName, lastName, dob, email, phone, nhs,
                        gender, address, postcode,
                        emergencyContactName, emergencyContactPhone, registrationDate, gpSurgeryId
                );
                patients.add(p);
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }

        return patients;
    }
}
