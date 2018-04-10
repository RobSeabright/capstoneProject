package Validation;

import Conversions.ConvertDates;
import Insurance.*;
import Managers.AccidentManager;
import Managers.VehicleManager;
import Managers.VehiclePolicyManager;
import Managers.VehicleQuoteManager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.Date;

@SessionScoped
@ManagedBean(name = "VehiclePolicyBean")
public class VehiclePolicyBean implements Serializable{

    @ManagedProperty("#{VehicleQuoteBean}")
    private VehicleQuoteBean vehicleQuoteBean;
    private PrimaryDriver driver;
    private VehicleQuote quote;
    private VehiclePolicy policy;
    private Vehicle vehicle;
    private String policyID;
    private double vehicleValue;
    private String vehicleModel;
    private String vehicleMake;
    private int vehicleYear;
    private String plateNumber;
    private Date startDate;
    private Date endDate;
    private Date displayStartDate;
    private Date displayEndDate;
    private double basePremium;
    private double tax;
    private double total;
    private int accidentsTotal;

    public VehicleQuoteBean getVehicleQuoteBean() {
        return vehicleQuoteBean;
    }

    public void setVehicleQuoteBean(VehicleQuoteBean vehicleQuoteBean) {
        this.vehicleQuoteBean = vehicleQuoteBean;
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

    public VehiclePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(VehiclePolicy policy) {
        this.policy = policy;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
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

    public int getAccidentsTotal() {
        return accidentsTotal;
    }

    public void setAccidentsTotal(int accidentsTotal) {
        this.accidentsTotal = accidentsTotal;
    }

    public String createPolicy() {

        VehiclePolicyManager vehiclePolicyManager = new VehiclePolicyManager();

        policy = vehiclePolicyManager.insertVehiclePolicy(vehicleQuoteBean.getQuote());
        vehicle = vehicleQuoteBean.getVehicle();

        policyID = policy.getPolicyID();
        vehicleValue = vehicle.getVehicleValue();
        vehicleModel = vehicle.getModel();
        vehicleMake = vehicle.getMake();
        vehicleYear = vehicle.getYear();
        plateNumber = vehicle.getPlateNumber();
        startDate = policy.getStartDate();
        endDate = policy.getEndDate();
        basePremium = policy.getBasePremium();
        tax = policy.getTax();
        total = policy.getTotal();
        accidentsTotal = vehicleQuoteBean.getAccidentsTotal();

        return "vehiclePolicy?faces-redirect=true";
    }


}
