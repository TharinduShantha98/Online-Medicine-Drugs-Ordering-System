package Entity;

public class Patient {
    String patientId;
    String firstName;
    String userName;
    String secondName;
    String idNumber;
    String password;
    String email;
    String address;
    String birthday;


    public Patient(String patientId, String firstName, String userName, String secondName, String idNumber,
                   String password, String email, String address, String birthday) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.userName = userName;
        this.secondName = secondName;
        this.idNumber = idNumber;
        this.password = password;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
    }

    public Patient() {
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }



}
