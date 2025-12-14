import java.time.LocalDate;

public class User {
    private String userId;
    private String firstName;
    private String lastName;
    private UserType type;
    private LocalDate dateOfBirth;
    private String email;
    private String phone;

    public User() { }

    public User(String userId,
                String firstName,
                String lastName,
                UserType type,
                LocalDate dateOfBirth,
                String email,
                String phone) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
        this.email = email;
        this.phone = phone;
    }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public UserType getType() { return type; }
    public void setType(UserType type) { this.type = type; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public boolean login(String username, String password) {
        // TODO: integrate with your controller/auth service
        return true;
    }

    public void logout() {
        // TODO: update any session state if you add it
    }
}


public enum UserType {
    PATIENT,
    CLINICIAN,
    ADMIN
}
