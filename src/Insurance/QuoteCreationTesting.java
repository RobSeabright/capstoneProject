package Insurance;

import Conversions.ConvertDates;
import Managers.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
//import com.sun.org.apache.xpath.internal.SourceTree;

public class QuoteCreationTesting {

    public static void main(String[] args) throws SQLException {

        //testing date conversions.
//        System.out.print(ConvertDates.convertStringToUtilDate("1993-09-30"));

        // Account manager test case
        AccountManager accountManager = new AccountManager();
        Account account = accountManager.createNewAccount("testemail@gmail.com", "password1");
        System.out.println(account.getUserId());
        ArrayList<Object> list = accountManager.accountLogin("testemail@gmail.com", "password1");
        System.out.println(list.get(0));
        //example of casting account
        Account account1 = (Account)list.get(1);
        System.out.println(account1.getUserId());

        //Home Owner Test case
        HomeOwnerManager homeOwnerManager = new HomeOwnerManager();
        HomeOwner homeOwner = homeOwnerManager.insertHomeOwner(account1.getUserId(), "Jon", "Doe", "1997-09-30", "1 prince philip drive", "st.Johns","NL", "A1E5M2","709-555-5555", "email@email.com", "F");
        System.out.println(homeOwner.getAddress());

        //Driver Test case
        DriversManager driversManager = new DriversManager();
        PrimaryDriver primaryDriver = driversManager.insertPrimaryDriver(account1.getUserId(), homeOwner.getFirstName(), homeOwner.getLastName(), "1997-09-30",
                homeOwner.getAddress(), homeOwner.getCity(),
                homeOwner.getProvince(), homeOwner.getPostalCode(), homeOwner.getPhoneNumber(), homeOwner.getEmail(), homeOwner.getGender(),
                "A3GH8657", "2017-10-22", 1);
        System.out.println(primaryDriver.getLicenceIssuedDate());

        //Home test Case
        HomeManager homeManager = new HomeManager();
        Home home = homeManager.insertHome(200000, 1995, 1, 3, "A1E5G7", "12 Fake Street", "St.Johns", "NL");
        System.out.println(home.getAddress());

        //homeQuote test
        HomeQuoteManager homeQuoteManager = new HomeQuoteManager();
        //NOTE: obviously premiums will need to be calculated with the calculations that rob wrote.
        HomeQuote homeQuote = homeQuoteManager.createNewQuote(4000.00,50.95,4050.96, home, homeOwner);
        System.out.println(homeQuote.getQuoteID());

        //Home Policy test case
        HomePolicyManager homePolicyManager = new HomePolicyManager();
        HomePolicy homePolicy = homePolicyManager.insertHomePolicy(homeQuote);
        System.out.println(homePolicy.getPolicyID());

        //vehicle test case
        VehicleManager vehicleManager = new VehicleManager();
        Vehicle vehicle = vehicleManager.insertVehicle(45000, "toyota", 2013, "corolla", "HXL456");
        System.out.println(vehicle.getVehicleID());

        //vehicleQuote test case
        VehicleQuoteManager vehicleQuoteManager = new VehicleQuoteManager();
        VehicleQuote vehicleQuote = vehicleQuoteManager.createNewQuote(4012.0,650,2451,vehicle, primaryDriver);
        System.out.println(vehicleQuote.getReplacementCost());

        //vehicle policy test case
        VehiclePolicyManager vehiclePolicyManger = new VehiclePolicyManager();
        VehiclePolicy vehiclePolicy = vehiclePolicyManger.insertVehiclePolicy(vehicleQuote);
        System.out.println(vehiclePolicy.getPolicyID());


        // Secondary Driver test case
        SecondaryDriver secondaryDriver = driversManager.insertSecondaryDriver( homeOwner.getFirstName(), homeOwner.getLastName(), "1997-09-30",
                homeOwner.getAddress(), homeOwner.getCity(),
                homeOwner.getProvince(), homeOwner.getPostalCode(), homeOwner.getPhoneNumber(), homeOwner.getEmail(), homeOwner.getGender(),
                "A3GH8657", "2017-10-22");

        System.out.println(secondaryDriver.getUserId());

        AccidentManager accidentManager = new AccidentManager();
        System.out.println(accidentManager.countFive(1));
        System.out.println(accidentManager.countTen(1));

    }
}
