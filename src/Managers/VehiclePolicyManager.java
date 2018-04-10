package Managers;

import Conversions.ConvertDates;
import Insurance.HomePolicy;
import Insurance.HomeQuote;
import Insurance.VehiclePolicy;
import Insurance.VehicleQuote;

import java.sql.*;

public class VehiclePolicyManager {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public VehiclePolicy insertVehiclePolicy(VehicleQuote vehicleQuote) {

        VehiclePolicy vehiclePolicy = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            statement = connection.createStatement();
            //Insert policy
            String query = "INSERT INTO VehiclePolicy(BasePremium, Tax, Total, ReplacementCostValue, UserID, VehicleID, QuoteID, StartDate, EndDate)\n" +
                    "VALUES(?,?,?,?,?,?,?,CURDATE(),DATE_ADD(CURDATE(), INTERVAL 1 YEAR));";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setDouble(1,vehicleQuote.getBasePremium());
            prepState.setDouble(2, vehicleQuote.getTax());
            prepState.setDouble(3, vehicleQuote.getTotal());
            prepState.setDouble(4,vehicleQuote.getReplacementCost());
            prepState.setInt(5,vehicleQuote.getUser().getUserId());
            prepState.setInt(6,vehicleQuote.getVehicle().getVehicleID());
            prepState.setString(7,vehicleQuote.getQuoteID());
            prepState.executeUpdate();


            query = "SELECT * FROM vehiclepolicy WHERE PolicyID = (SELECT MAX(PolicyID) FROM vehiclepolicy)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();
            vehiclePolicy = new VehiclePolicy(resultSet.getString("PolicyID"), vehicleQuote.getUser(), vehicleQuote, ConvertDates.convertToUtilDate(resultSet.getDate("StartDate")),ConvertDates.convertToUtilDate(resultSet.getDate("EndDate")) , vehicleQuote.getBasePremium(), vehicleQuote.getTax(), vehicleQuote.getTotal(), vehicleQuote.getVehicle(), vehicleQuote.getVehicle().getVehicleValue());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return vehiclePolicy;
    }

    private void closeConnections(Statement statement, ResultSet resultSet, Connection connection ){
        try {
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
