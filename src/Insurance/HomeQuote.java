package Insurance;

import java.util.Date;

public class HomeQuote extends Quote {

    private Home home;
    private double replacementCost;
    private double liabilityLimit;
    private double contentsLimit;
    private double contentsDeductible;

    //TODO change User to homeOwner and remove useless parameters add premium, tax, total etc.
    public HomeQuote(String quoteID, User user, Date startDate, Date endDate, double basePremium, double tax,
                     double total, Home home, double replacementCost, double liabilityLimit, double contentsLimit,
                     double contentsDeductible) {
        super(quoteID, user, startDate, endDate, basePremium, tax, total);
        this.home = home;
        this.replacementCost = replacementCost;
        this.liabilityLimit = liabilityLimit;
        this.contentsLimit = contentsLimit;
        this.contentsDeductible = contentsDeductible;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public double getLiabilityLimit() {
        return liabilityLimit;
    }

    public void setLiabilityLimit(double liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    public double getContentsLimit() {
        return contentsLimit;
    }

    public void setContentsLimit(double contentsLimit) {
        this.contentsLimit = contentsLimit;
    }

    public double getContentsDeductible() {
        return contentsDeductible;
    }

    public void setContentsDeductible(double contentsDeductible) {
        this.contentsDeductible = contentsDeductible;
    }
}
