package Validation;

import Insurance.Driver;
import Insurance.PrimaryDriver;
import Managers.AccidentManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name="AccidentBean")
public class AccidentBean {

    @ManagedProperty("#{UserBean}")
    private UserBean userBean;
    private PrimaryDriver driver;
    private int secondaryID;
    private int yearOfAccident;
    private Boolean responsible;

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public PrimaryDriver getDriver() {
        return driver;
    }

    public void setDriver(PrimaryDriver driver) {
        this.driver = driver;
    }

    public int getSecondaryID() {
        return secondaryID;
    }

    public void setSecondaryID(int secondaryID) {
        this.secondaryID = secondaryID;
    }

    public int getYearOfAccident() {
        return yearOfAccident;
    }

    public void setYearOfAccident(int yearOfAccident) {
        this.yearOfAccident = yearOfAccident;
    }

    public Boolean getResponsible() {
        return responsible;
    }

    public void setResponsible(Boolean responsible) {
        this.responsible = responsible;
    }

    public String addAccident() {

        this.setDriver(userBean.getDriver());
        AccidentManager manager = new AccidentManager();

        manager.insertAccident(driver.getUserId(), secondaryID, yearOfAccident, responsible);

        return "vehicleQuoteAdditions?faces-redirect=true";
    }

}
