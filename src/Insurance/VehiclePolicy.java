package Insurance;

import java.util.Date;

public class VehiclePolicy extends Policy {

    private Vehicle vehicle;
    private double replacementCosts;

    public VehiclePolicy(String policyID, User user, Quote quote, Date startDate, Date endDate, double basePremium, double tax, double total, Vehicle vehicle, double replacementCosts) {
        super(policyID, user, quote, startDate, endDate, basePremium, tax, total);
        this.vehicle = vehicle;
        this.replacementCosts = replacementCosts;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public double getReplacementCosts() {
        return replacementCosts;
    }

    public void setReplacementCosts(double replacementCosts) {
        this.replacementCosts = replacementCosts;
    }
}
