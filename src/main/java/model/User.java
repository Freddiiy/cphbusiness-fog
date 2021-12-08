package model;

public class User {
    private int id;
    private String email;
    private String fname;
    private String lname;
    private String password;
    private String role;
    private String sessionID;
    private String address;
    private int zipcode;
    private String city;
    private String phone;

    public User(String email, String password, String role, String sessionID) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.sessionID = sessionID;
    }

    public User(int id, String email, String fname, String lname, String role) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
    }

    public User(String email, String fname, String lname, String password, String role, String sessionID) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.password = password;
        this.role = role;
        this.sessionID = sessionID;
    }

    public User(int id, String email, String fname, String lname, String role, String sessionID) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.sessionID = sessionID;
    }

    public User(int id, String email, String fname, String lname, String role, String sessionID, String address, int zipcode, String city, String phone) {
        this.id = id;
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.role = role;
        this.sessionID = sessionID;
        this.address = address;
        this.zipcode = zipcode;
        this.city = city;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionID() {
        return sessionID;
    }

    public void setSessionID(String sessionID) {
        this.sessionID = sessionID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
