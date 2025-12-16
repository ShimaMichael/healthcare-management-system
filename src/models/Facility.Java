package models;
import java.util.ArrayList;
import java.util.List;

public class Facility {
    private String facilityId;
    private String name;
    private String type;         // e.g. GP practice, hospital
    private String address;
    private String postcode;
    private String phoneNumber;
    private String email;
    private String openingHours;
    private String managerName;
    private int capacity;
    private List<String> specialities;

    public Facility() {
        this.specialities = new ArrayList<>();
    }

    public Facility(String facilityId,
                    String name,
                    String type,
                    String address,
                    String postcode,
                    String phoneNumber,
                    String email,
                    String openingHours,
                    String managerName,
                    int capacity,
                    List<String> specialities) {
        this.facilityId = facilityId;
        this.name = name;
        this.type = type;
        this.address = address;
        this.postcode = postcode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.openingHours = openingHours;
        this.managerName = managerName;
        this.capacity = capacity;
        this.specialities = specialities != null ? new ArrayList<>(specialities) : new ArrayList<>();
    }

    public String getFacilityId() { return facilityId; }
    public void setFacilityId(String facilityId) { this.facilityId = facilityId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getPostcode() { return postcode; }
    public void setPostcode(String postcode) { this.postcode = postcode; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getOpeningHours() { return openingHours; }
    public void setOpeningHours(String openingHours) { this.openingHours = openingHours; }

    public String getManagerName() { return managerName; }
    public void setManagerName(String managerName) { this.managerName = managerName; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<String> getSpecialities() { return specialities; }
    public void setSpecialities(List<String> specialities) {
        this.specialities = specialities != null ? new ArrayList<>(specialities) : new ArrayList<>();
    }

    public void addSpeciality(String speciality) {
        this.specialities.add(speciality);
    }
}
