package fileIO;

import models.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class DataLoader {

    private final String patientFilePath      = "data/patients.csv";
    private final String cliniciansFilePath   = "data/clinicians.csv";
    private final String facilityFilePath     = "data/facilities.csv";
    private final String appointmentFilePath  = "data/appointments.csv";
    private final String prescriptionFilePath = "data/prescriptions.csv";
    private final String referralFilePath     = "data/referrals.csv";
    private final String staffFilePath        = "data/staff.csv";

    private <T> ArrayList<T> load(String path, Function<String, T> mapper) {
        ArrayList<T> result = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line = br.readLine(); // skip header
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                T obj = mapper.apply(line);
                if (obj != null) {
                    result.add(obj);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading " + path + ": " + e.getMessage());
        }

        return result;
    }

    public ArrayList<Patient> loadPatients() {
        return load(patientFilePath, this::parsePatient);
    }

    public ArrayList<Clinician> loadClinicians() {
        return load(cliniciansFilePath, this::parseClinician);
    }

    public ArrayList<Facility> loadFacilities() {
        return load(facilityFilePath, this::parseFacility);
    }

    public ArrayList<Appointment> loadAppointments() {
        return load(appointmentFilePath, this::parseAppointment);
    }

    public ArrayList<Prescription> loadPrescriptions() {
        return load(prescriptionFilePath, this::parsePrescription);
    }

    public ArrayList<Referral> loadReferrals() {
        return load(referralFilePath, this::parseReferral);
    }

    public ArrayList<AdminStaff> loadAdmins() {
        return load(staffFilePath, this::parseAdminStaff);
    }


    private Patient parsePatient(String line) {
        String[] f = line.split(",");

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

        return new Patient(
                userId, firstName, lastName, dob, email, phone, nhs,
                gender, address, postcode,
                emergencyContactName, emergencyContactPhone, registrationDate, gpSurgeryId
        );
    }

    private Clinician parseClinician(String line) {
        String[] f = line.split(",");

        String userId           = f[0].trim();
        String firstName        = f[1].trim();
        String lastName         = f[2].trim();
        String title            = f[3].trim();
        String specialty        = f[4].trim();
        String gmcNumber        = f[5].trim();
        String phone            = f[6].trim();
        String email            = f[7].trim();
        String workplaceId      = f[8].trim();
        String workplaceType    = f[9].trim();
        String employmentStatus = f[10].trim();
        LocalDate startDate     = LocalDate.parse(f[11].trim());

        return new Clinician(
                userId, firstName, lastName, email, phone, userId,
                title, specialty, workplaceId, workplaceType,
                employmentStatus, startDate, gmcNumber
        );
    }

    private Facility parseFacility(String line) {
        String[] f = line.split(",");

        String facilityId   = f[0].trim();
        String facilityName = f[1].trim();
        String facilityType = f[2].trim();
        String address      = (f[3] + "," + f[4]).replace("\"", "").trim();
        String postcode     = f[5].trim();
        String email        = f[6].trim();
        String phone        = f[7].trim();
        String openingHours = (f[8] + "," + f[9]).replace("\"", "").trim();
        String managerName  = f[10].trim();
        int capacity        = Integer.parseInt(f[11].trim());

        String[] specialitiesOffered = f[12].trim().split("\\|");
        List<String> specialityList = new ArrayList<>();
        for (String s : specialitiesOffered) {
            if (!s.isBlank()) {
                specialityList.add(s.trim());
            }
        }

        return new Facility(
                facilityId, facilityName, facilityType, address, postcode,
                phone, email, openingHours, managerName, capacity, specialityList
        );
    }

    private Appointment parseAppointment(String line) {
        String[] f = line.split(",");

        String appointmentId   = f[0].trim();
        String patientId       = f[1].trim();
        String clinicianId     = f[2].trim();
        String facilityId      = f[3].trim();
        LocalDate appointmentDate = LocalDate.parse(f[4].trim());
        String appointmentTime = f[5].trim();
        int durationMinutes    = Integer.parseInt(f[6].trim());
        String appointmentType = f[7].trim();
        String status          = f[8].trim();
        String reasonForVisit  = f[9].trim();
        String notes           = f[10].trim();
        LocalDate createdDate  = LocalDate.parse(f[11].trim());
        LocalDate lastModified = LocalDate.parse(f[12].trim());

        return new Appointment(
                appointmentId, patientId, clinicianId, facilityId,
                appointmentDate, appointmentTime,
                durationMinutes, appointmentType, status,
                reasonForVisit, notes, createdDate, lastModified
        );
    }

    private Prescription parsePrescription(String line) {
        String[] f = line.split(",");

        String prescriptionId  = f[0].trim();
        String patientId       = f[1].trim();
        String clinicianId     = f[2].trim();
        String appointmentId   = f[3].trim().isEmpty() ? null : f[3].trim();
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
        LocalDate collectionDate = null;
        if (f.length == 15 && !f[14].trim().isEmpty()) {
            collectionDate = LocalDate.parse(f[14].trim());
        }

        return new Prescription(
                prescriptionId, patientId, clinicianId, appointmentId,
                prescriptionDate, medicationName, dosage, frequency,
                durationDays, quantity, instructions, pharmacyName,
                status, issueDate, collectionDate
        );
    }

    private Referral parseReferral(String line) {
        String[] f = line.split(",");

        String referralId           = f[0].trim();
        String patientId            = f[1].trim();
        String referringClinicianId = f[2].trim();
        String referredClinicianId  = f[3].trim();
        String referringFacilityId  = f[4].trim();
        String referredFacilityId   = f[5].trim();
        LocalDate referralDate      = LocalDate.parse(f[6].trim());
        String urgencyLevel         = f[7].trim();
        String referralReason       = f[8].trim();
        String clinicalSummary      = f[9].trim();
        String requestedInvestigations = f[10].trim();
        String status               = f[11].trim();
        String appointmentId        = f[12].trim().isEmpty() ? null : f[12].trim();

        int lastIndex = f.length - 1;
        LocalDate lastUpdated = LocalDate.parse(f[lastIndex].trim());
        LocalDate createdDate = LocalDate.parse(f[lastIndex - 1].trim());

        StringBuilder notesBuilder = new StringBuilder();
        for (int i = 13; i < lastIndex - 1; i++) {
            if (notesBuilder.length() > 0) {
                notesBuilder.append(",");
            }
            notesBuilder.append(f[i]);
        }
        String notes = notesBuilder.toString().trim();

        return new Referral(
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
    }

    private AdminStaff parseAdminStaff(String line) {
        String[] f = line.split(",");

        String staffId          = f[0].trim();
        String firstName        = f[1].trim();
        String lastName         = f[2].trim();
        String role             = f[3].trim();
        String department       = f[4].trim();
        String facilityId       = f[5].trim();
        String phone            = f[6].trim();
        String email            = f[7].trim();
        String employmentStatus = f[8].trim();
        LocalDate startDate     = LocalDate.parse(f[9].trim());
        String lineManager      = f[10].trim();
        String accessLevel      = f[11].trim();

        return new AdminStaff(
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
    }
}
