package Insurance;

import java.util.Date;

public class HomePolicy extends Policy {

    private Home home;

    public HomePolicy(String policyID, User user, Quote quote, Date startDate, Date endDate, double basePremium, double tax, double total, Home home) {
        super(policyID, user, quote, startDate, endDate, basePremium, tax, total);
        this.home = home;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }
}