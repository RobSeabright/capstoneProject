package Validation;

import Conversions.ConvertDates;
import Insurance.*;
import Managers.HomeManager;
import Managers.HomePolicyManager;
import Managers.HomeQuoteManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

@SessionScoped
@ManagedBean(name = "HomePolicyBean")
public class HomePolicyBean implements Serializable{


    @ManagedProperty("#{HomeQuoteBean}")
    private HomeQuoteBean homeQuoteBean;
    private HomePolicy homePolicy;
    private Home home;
    private String policyID;
    private double value;
    private int homeYear;
    private int homeType;
    private int heatingType;
    private String homeTypeDisplay;
    private String heatingTypeDisplay;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private Date startDate;
    private Date endDate;
    private Date displayStartDate;
    private Date displayEndDate;
    private double basePremium;
    private double tax;
    private double total;

    public HomeQuoteBean getHomeQuoteBean() {
        return homeQuoteBean;
    }

    public void setHomeQuoteBean(HomeQuoteBean homeQuoteBean) {
        this.homeQuoteBean = homeQuoteBean;
    }

    public HomePolicy getHomePolicy() {
        return homePolicy;
    }

    public void setHomePolicy(HomePolicy homePolicy) {
        this.homePolicy = homePolicy;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public String getQuoteID() {
        return policyID;
    }

    public void setQuoteID(String policyID) {
        this.policyID = policyID;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getHomeYear() {
        return homeYear;
    }

    public void setHomeYear(int homeYear) {
        this.homeYear = homeYear;
    }

    public int getHomeType() {
        return homeType;
    }

    public void setHomeType(int homeType) {
        this.homeType = homeType;
    }

    public String getHomeTypeDisplay() {
        return homeTypeDisplay;
    }

    public void setHomeTypeDisplay() {
        switch (this.homeType) {
            case 1: homeTypeDisplay = "Single Dwelling";
                break;
            case 2: homeTypeDisplay = "Apartment";
                break;
            case 3: homeTypeDisplay = "Bungalow";
                break;
            case 4: homeTypeDisplay = "Semi-Attached";
                break;
        }
    }

    public int getHeatingType() {
        return heatingType;
    }

    public void setHeatingType(int heatingType) {
        this.heatingType = heatingType;
    }

    public String getHeatingTypeDisplay() {
        return heatingTypeDisplay;
    }

    public void setHeatingTypeDisplay() {
        switch (this.heatingType) {
            case 1: heatingTypeDisplay = "Electric";
                break;
            case 2: heatingTypeDisplay = "Oil";
                break;
            case 3: heatingTypeDisplay = "Wood";
                break;
            case 4: heatingTypeDisplay = "Gas";
                break;
            case 5: heatingTypeDisplay = "Other";
                break;
        }
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getDisplayStartDate() {
        displayStartDate = startDate;

        return ConvertDates.convertToDisplayDate(displayStartDate);

    }

    public String getDisplayEndDate() {
        displayEndDate = endDate;

        return ConvertDates.convertToDisplayDate(displayEndDate);
    }

    public double getBasePremium() {
        return Math.round(basePremium * 100)/100;
    }

    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getTax() {
        return Math.round(tax * 100)/100;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return Math.round(total * 100)/100;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String createPolicy() {

        HomePolicyManager homePolicyManager = new HomePolicyManager();

        homePolicy = homePolicyManager.insertHomePolicy(homeQuoteBean.getHomeQuote());
        home = homeQuoteBean.getHome();

        policyID = homePolicy.getPolicyID();
        value = home.getValue();
        homeYear = home.getYearBuilt();
        homeType = home.getHomeType();
        heatingType = home.getHeatingType();
        address = home.getAddress();
        city = home.getCity();
        province = home.getProvince();
        postalCode = home.getPostalCode();
        startDate = homePolicy.getStartDate();
        endDate = homePolicy.getEndDate();
        basePremium = homePolicy.getBasePremium();
        tax = homePolicy.getTax();
        total = homePolicy.getTotal();
        setHomeTypeDisplay();
        setHeatingTypeDisplay();

        return "homePolicy?faces-redirect=true";
    }


}
