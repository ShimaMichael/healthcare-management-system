package fileIO;

import models.Patient;

import java.io.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class PatientsFileIO {

    private final String filepath;


    public PatientsFileIO(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Patient> loadFile() throws FileNotFoundException {
        ArrayList<Patient> patients = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(this.filepath))){
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
