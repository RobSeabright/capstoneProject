package Insurance;

public class Vehicle {
    private int vehicleID;
    private double vehicleValue;
    private String make;
    private int year;
    private String model;
    private String plateNumber;

    public Vehicle(int vehicleID, double vehicleValue, String make, int year, String model, String plateNumber) {
        this.vehicleID = vehicleID;
        this.vehicleValue = vehicleValue;
        this.make = make;
        this.year = year;
        this.model = model;
        this.plateNumber = plateNumber;
    }

    public int getVehicleID() {
        return vehicleID;
    }

    public double getVehicleValue() {
        return vehicleValue;
    }

    public String getMake() {
        return make;
    }

    public int getYear() {
        return year;
    }

    public String getModel() {
        return model;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setVehicleID(int vehicleID) {
        this.vehicleID = vehicleID;
    }

    public void setVehicleValue(double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }
}
