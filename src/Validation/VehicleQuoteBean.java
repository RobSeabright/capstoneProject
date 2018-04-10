package Validation;

import Conversions.ConvertDates;
import Insurance.PrimaryDriver;
import Insurance.Vehicle;
import Insurance.VehiclePremium;
import Insurance.VehicleQuote;
import Managers.AccidentManager;
import Managers.VehicleManager;
import Managers.VehicleQuoteManager;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;


@SessionScoped
@ManagedBean(name = "VehicleQuoteBean")
public class VehicleQuoteBean implements Serializable{

    @ManagedProperty("#{UserBean}")
    private UserBean userBean;
    private PrimaryDriver driver;
    private VehicleQuote quote;
    private Vehicle vehicle;
    private String quoteID;
    private double vehicleValue;
    private String vehicleModel;
    private String vehicleMake;
    private int vehicleYear;
    private String plateNumber;
    private Date startDate;
    private Date displayStartDate;
    private Date endDate;
    private Date displayEndDate;
    private double basePremium;
    private double tax;
    private double total;
    private int accidentsIn5;
    private int accidentsIn10;
    private int accidentsTotal;

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

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleQuote getQuote() {
        return quote;
    }

    public void setQuote(VehicleQuote quote) {
        this.quote = quote;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    public double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public int getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(int vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getDisplayStartDate() {
        displayStartDate = startDate;

        return ConvertDates.convertToDisplayDate(displayStartDate);

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

    public int getAccidentsIn5() {
        return accidentsIn5;
    }

    public void setAccidentsIn5(int accidentsIn5) {
        this.accidentsIn5 = accidentsIn5;
    }

    public int getAccidentsIn10() {
        return accidentsIn10;
    }

    public void setAccidentsIn10(int accidentsIn10) {
        this.accidentsIn10 = accidentsIn10;
    }

    public int getAccidentsTotal() {
        return accidentsTotal;
    }

    public void setAccidentsTotal(int accidentsTotal) {
        this.accidentsTotal = accidentsTotal;
    }

    public String createQuote() {

        VehicleManager vehicleManager = new VehicleManager();
        VehicleQuoteManager quoteManager = new VehicleQuoteManager();
        AccidentManager accidentManager = new AccidentManager();

        this.setDriver(userBean.getDriver());
        vehicle = vehicleManager.insertVehicle(vehicleValue, vehicleMake, vehicleYear, vehicleModel, plateNumber);
        accidentsIn5 = accidentManager.countFive(driver.getUserId());
        accidentsIn10 = accidentManager.countTen(driver.getUserId());
        accidentsTotal = accidentsIn5 + accidentsIn10;

        //Disgusting method of getting year from a date.
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(driver.getLicenceIssuedDate());
        int year = calendar.get(Calendar.YEAR);

        basePremium = VehiclePremium.calcPremium(vehicleValue, year, driver.getLocationCode(), accidentsIn5, accidentsIn10);

        tax = basePremium * 0.15;
        total = basePremium * 1.15;

        quote = quoteManager.createNewQuote(basePremium, tax, total, vehicle, driver);

        quoteID = quote.getQuoteID();
        startDate = quote.getStartDate();
        endDate = quote.getEndDate();

        return "vehicleQuoteResult?faces-redirect=true";
    }

}
