package Validation;

import Insurance.HomeOwner;
import Insurance.PrimaryDriver;
import Managers.DriversManager;
import Managers.HomeOwnerManager;

import java.io.Serializable;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.validation.Validation;

@SessionScoped
@ManagedBean(name = "UserBean")
public class UserBean implements Serializable {


    @ManagedProperty("#{AccountBean}")
    private AccountBean accountBean;
    private HomeOwner homeOwner;
    private PrimaryDriver driver;
    private int userId;
    private String firstName;
    private String lastName;
    private String gender;
    private String dob;
    private String phone;
    private String email;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String licenseNumber;
    private String licenseDate;
    private int location;

    public AccountBean getAccountBean() {
        return accountBean;
    }

    public void setAccountBean(AccountBean accountBean) {
        this.accountBean = accountBean;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public HomeOwner getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(HomeOwner homeOwner) {
        this.homeOwner = homeOwner;
    }

    public PrimaryDriver getDriver() {
        return driver;
    }

    public void setDriver(PrimaryDriver driver) {
        this.driver = driver;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(String licenseDate) {
        this.licenseDate = licenseDate;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String createHomeOwner() {

        setUserId(accountBean.getUserId());

        HomeOwnerManager manager = new HomeOwnerManager();

        homeOwner = manager.insertHomeOwner(userId, firstName, lastName, dob, address, city, province,
                postalCode, phone, email, gender);

        return "homeQuote?faces-redirect=true";
    }

    public String createPrimaryDriver() {

        setUserId(accountBean.getUserId());

        DriversManager manager = new DriversManager();

        driver = manager.insertPrimaryDriver(userId, firstName, lastName, dob, address, city, province,
                postalCode, phone, email, gender, licenseNumber, licenseDate, location);

        return "vehicleQuote?faces-redirect=true";
    }

    public String updateUserAccount() {

        Managers.HomeOwnerManager manager = new HomeOwnerManager();

        manager.updateHomeOwner(userId, firstName, lastName, dob, address, city, province, postalCode, phone, email, gender);

        return "viewUserProfile?faces-redirect=true";
    }



}
