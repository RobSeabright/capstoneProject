package Validation;

import Conversions.ConvertDates;
import Insurance.Home;
import Insurance.HomeOwner;
import Insurance.HomePremium;
import Insurance.HomeQuote;
import Managers.HomeManager;
import Managers.HomeQuoteManager;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "HomeQuoteBean")
public class HomeQuoteBean implements Serializable{

    @ManagedProperty("#{UserBean}")
    private UserBean userBean;
    private HomeOwner homeOwner;
    private HomeQuote homeQuote;
    private Home home;
    private String quoteID;
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }

    public HomeOwner getHomeOwner() {
        return homeOwner;
    }

    public void setHomeOwner(HomeOwner homeOwner) {
        this.homeOwner = homeOwner;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    public HomeQuote getHomeQuote() {
        return homeQuote;
    }

    public void setHomeQuote(HomeQuote homeQuote) {
        this.homeQuote = homeQuote;
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


    public String createQuote() {

        HomeManager homeManager = new HomeManager();
        HomeQuoteManager homeQuoteManager = new HomeQuoteManager();

        setHomeOwner(userBean.getHomeOwner());

        home = homeManager.insertHome(value, homeYear, homeType, heatingType, postalCode,
                address, city, province);

        basePremium = HomePremium.calcPremium(value, homeYear, homeType, heatingType);

        tax = basePremium * 0.15;
        total = basePremium * 1.15;

        homeQuote = homeQuoteManager.createNewQuote(basePremium, tax, total, home, homeOwner);

        quoteID = homeQuote.getQuoteID();
        startDate = homeQuote.getStartDate();
        endDate = homeQuote.getEndDate();
        setHomeTypeDisplay();
        setHeatingTypeDisplay();

        return "homeQuoteResult?faces-redirect=true";
    }
}
