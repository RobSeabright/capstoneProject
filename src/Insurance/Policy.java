package Insurance;

import java.util.Date;

public abstract class Policy {

    private String policyID;
    private User user;
    private Quote quote;
    private Date startDate;
    private Date endDate;
    private double basePremium;
    private double tax;
    private double total;

    public Policy(String policyID, User user, Quote quote, Date startDate, Date endDate, double basePremium, double tax, double total) {
        this.policyID = policyID;
        this.user = user;
        this.quote = quote;
        this.startDate = startDate;
        this.endDate = endDate;
        this.basePremium = basePremium;
        this.tax = tax;
        this.total = total;
    }

    public String getPolicyID() {
        return policyID;
    }

    public void setPolicyID(String policyID) {
        this.policyID = policyID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
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

    public double getBasePremium() {
        return basePremium;
    }

    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
