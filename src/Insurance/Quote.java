package Insurance;

import java.util.Date;

public abstract class Quote {

    private String quoteID;
    private User user;
    private Date startDate;
    private Date endDate;
    private double basePremium;
    private double tax;
    private double total;

    public Quote(String quoteID, User user, Date startDate, Date endDate, double basePremium, double tax, double total) {
        this.quoteID = quoteID;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.basePremium = basePremium;
        this.tax = tax;
        this.total = total;
    }

    public String getQuoteID() {
        return quoteID;
    }

    public void setQuoteID(String quoteID) {
        this.quoteID = quoteID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
