package Insurance;

import java.util.Date;

public class VehicleQuote extends Quote {

    private Vehicle vehicle;
    private double replacementCost;

    public VehicleQuote(String quoteID, User user, Date startDate, Date endDate, double basePremium, double tax,
                        double total, Vehicle vehicle, double replacementCost) {
        super(quoteID, user, startDate, endDate, basePremium, tax, total);
        this.vehicle = vehicle;
        this.replacementCost = replacementCost;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }
}
