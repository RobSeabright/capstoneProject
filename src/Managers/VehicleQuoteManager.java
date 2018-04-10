package Managers;

import Conversions.ConvertDates;
import Insurance.PrimaryDriver;
import Insurance.Vehicle;
import Insurance.VehicleQuote;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

//TODO: Add second quote creation that takes secondary Driver in as a object.
public class VehicleQuoteManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * Creates a new Quote based on specific inputs
     * @param basePremium basepremium double
     * @param tax tax double
     * @param total total double
     * @param vehicle vehicle object
     * @param primaryDriver primary driver object
     * @return
     */
    public VehicleQuote createNewQuote(double basePremium, double tax, double total, Vehicle vehicle, PrimaryDriver primaryDriver){
        VehicleQuote quote = new VehicleQuote("", primaryDriver,null,null, basePremium, tax, total, vehicle,vehicle.getVehicleValue());

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO VehicleQuotes( BasePremium, Tax, Total, VehicleID, ReplacementCost, UserID, DateQuoted, QuoteExpiredDate)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY))";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setDouble(1, basePremium);
            prepState.setDouble(2, tax);
            prepState.setDouble(3, total);
            prepState.setInt(4,vehicle.getVehicleID());
            prepState.setDouble(5, vehicle.getVehicleValue());
            prepState.setInt(6, primaryDriver.getUserId());
            prepState.executeUpdate();
            //Get created quote
            query = "SELECT * FROM VehicleQuotes WHERE QuoteID = (SELECT MAX(QuoteID) FROM VehicleQuotes)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();
            quote.setQuoteID(resultSet.getString("QuoteID"));
            quote.setBasePremium(resultSet.getDouble("BasePremium"));
            quote.setTax(resultSet.getDouble("Tax"));
            quote.setTotal(resultSet.getDouble("Total"));
            quote.setStartDate(ConvertDates.convertToUtilDate(resultSet.getDate("DateQuoted")));
            quote.setEndDate(ConvertDates.convertToUtilDate(resultSet.getDate("QuoteExpiredDate")));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return quote;
    }

    /**
     * Vehicle qupte selects a quote
     * @param quoteID quoteID string
     * @param vehicle vehicle object
     * @param primaryDriver primaryDriver object.
     * @return returns quote object
     */
    public VehicleQuote selectQuote(String quoteID, Vehicle vehicle, PrimaryDriver primaryDriver){
        VehicleQuote quote = new VehicleQuote("", primaryDriver,null,null, 0, 0, 0, vehicle,0);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "SELECT * FROM vehiclequotes WHERE QuoteID = ?";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setString(1, quoteID);
            //execute query
             resultSet = prepState.executeQuery();
            resultSet.next();
            //populate quote
            quote.setQuoteID(resultSet.getString("QuoteID"));
            quote.setBasePremium(resultSet.getDouble("BasePremium"));
            quote.setTax(resultSet.getDouble("Tax"));
            quote.setTotal(resultSet.getDouble("Total"));
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return quote;
    }

    /**
     * closeConnections servers as a function to close all connections to the database.
     * @param statement statement
     * @param resultSet resultSet
     * @param connection connection
     */
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
