package Insurance;

import java.util.Calendar;

public class HomePremium {

    private static final double basePremium = 280.00;
    private static final double valueBase = 250000.00;
    private static final double valueFactor = 0.001;

    public static double calcPremium (double homeVal, int yearBuilt, int homeType, int heatingType) {
        return calcValuePremium(homeVal) * getAgeFactor(yearBuilt) * getTypeFactor(homeType) * getHeatingFactor(heatingType);
    }

    private static double calcValuePremium(double homeVal) {
        if ((homeVal - valueBase) > 0) {
            return (homeVal - valueBase) * valueFactor + basePremium;
        }
        else {
            return basePremium;
        }
    }

    private static double getAgeFactor(int yearBuilt) {
        int homeAge = Calendar.getInstance().get(Calendar.YEAR) - yearBuilt;

        if (homeAge < 20) {
            return 1.00;
        }
        else if (homeAge < 50) {
            return 1.25;
        }
        else {
            return 1.5;
        }
    }

    private static double getTypeFactor(int homeType) {
        if (homeType == 1){
            return 1.00;
        }
        else if (homeType == 2) {
            return 0.75;
        }
        else if (homeType == 3) {
            return 1.00;
        }
        else {
            return 1.15;
        }
    }

    private static double getHeatingFactor(int heatingType) {
        if (heatingType == 1) {
            return 1.00;
        }
        else if (heatingType == 2) {
            return 1.75;
        }
        else if (heatingType == 3) {
            return 1.30;
        }
        else if (heatingType == 4) {
            return 1.40;
        }
        else {
            return 1.25;
        }
    }

}
