package Managers;

import Conversions.ConvertDates;
import Insurance.PrimaryDriver;
import Insurance.SecondaryDriver;


import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.*;

public class DriversManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    public PrimaryDriver insertPrimaryDriver(int userId, String firstName, String lastName, String dateOfBirth, String address, String city,
                                        String province, String postalCode, String phoneNumber, String email, String gender,
                                        String driversLicenceNumber, String licenseDateIssued, int locationCode){

        PrimaryDriver driver = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO PrincipleDriver(UserID, FirstName, LastName, DOB, Address, City, Province, PostalCode, PhoneNumber, Email, Gender, DriversLicenseNumber, LicenseIssueDate, LocationID)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?, ?, ?);";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setInt(1,userId);
            prepState.setString(2,firstName);
            prepState.setString(3,lastName);
            prepState.setDate(4, ConvertDates.convertStringToSqlDate(dateOfBirth));
            prepState.setString(5, address);
            prepState.setString(6, city);
            prepState.setString(7, province);
            prepState.setString(8, postalCode);
            prepState.setString(9, phoneNumber);
            prepState.setString(10, email);
            prepState.setString(11, gender);
            prepState.setString(12, driversLicenceNumber);
            prepState.setDate(13, ConvertDates.convertStringToSqlDate(licenseDateIssued));
            prepState.setInt(14,locationCode);
            prepState.executeUpdate();
            //create driver object

            driver = new PrimaryDriver(userId, firstName, lastName, ConvertDates.convertStringToUtilDate(dateOfBirth), address, city,
                    province, postalCode, phoneNumber, email, gender,
                    driversLicenceNumber, ConvertDates.convertStringToUtilDate(licenseDateIssued), locationCode);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnectionsNoResult(statement, connection);
        }
        return driver;
    }

    public SecondaryDriver insertSecondaryDriver(String firstName, String lastName, String dateOfBirth, String address, String city,
                                               String province, String postalCode, String phoneNumber, String email, String gender,
                                               String driversLicenceNumber, String licenseDateIssued){

        SecondaryDriver driver = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO SecondaryDriver(FirstName, LastName, DOB, Address, City, Province, PostalCode, PhoneNumber, Email, Gender, DriversLicense, LicenseIssueDate)\n" +
                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?,?, ?, ?);";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setString(1,firstName);
            prepState.setString(2,lastName);
            prepState.setDate(3, ConvertDates.convertStringToSqlDate(dateOfBirth));
            prepState.setString(4, address);
            prepState.setString(5, city);
            prepState.setString(6, province);
            prepState.setString(7, postalCode);
            prepState.setString(8, phoneNumber);
            prepState.setString(9, email);
            prepState.setString(10, gender);
            prepState.setString(11, driversLicenceNumber);
            prepState.setDate(12, ConvertDates.convertStringToSqlDate(dateOfBirth));
            prepState.executeUpdate();
            //create driver object

            query = "SELECT * FROM SecondaryDriver WHERE SecondaryID  = (SELECT MAX(SecondaryID) FROM SecondaryDriver)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();


            driver = new SecondaryDriver(resultSet.getInt("SecondaryID"), firstName, lastName, ConvertDates.convertStringToUtilDate(dateOfBirth), address, city,
                    province, postalCode, phoneNumber, email, gender,
                    driversLicenceNumber, ConvertDates.convertStringToUtilDate(licenseDateIssued));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet, connection);
        }
        return driver;
    }

    public PrimaryDriver selectPrimaryDriver(int userID){
        PrimaryDriver driver = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "SELECT * FROM PrincipleDriver WHERE UserID = ?";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setInt(1, userID);
            //execute query
            resultSet = prepState.executeQuery();
            resultSet.next();

            driver = new PrimaryDriver(resultSet.getInt("UserID"), resultSet.getString("FirstName"),
                    resultSet.getString("LastName"), ConvertDates.convertToSqlDate(resultSet.getDate("DOB")),
                    resultSet.getString("Address"), resultSet.getString("City"), resultSet.getString("Province"),
                    resultSet.getString("PostalCode"), resultSet.getString("PhoneNumber"),
                    resultSet.getString("Email"), resultSet.getString("Gender"), resultSet.getString("DriversLicenseNumber"),
                    ConvertDates.convertToUtilDate(resultSet.getDate("LicenseIssueDate")), resultSet.getInt("LocationID"));

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return driver;
    }

    public PrimaryDriver updatePrimaryDriver(int userId, String firstName, String lastName, String dateOfBirth, String address, String city,
                                              String province, String postalCode, String phoneNumber, String email, String gender,
                                              String driversLicenceNumber, String licenseDateIssued, int locationCode){
        PrimaryDriver driver = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = " UPDATE PrincipleDriver SET FirstName = ?, LastName = ?, DOB = ?, Address = ?, City = ?, Province = ?, PostalCode = ?,  Email = ?, Gender = ?, DriversLicenseNumber = ?, LicenseIssueDate = ? , LocationID = ? WHERE UserID = ?";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setString(1,firstName);
            prepState.setString(2,lastName);
            prepState.setDate(3, ConvertDates.convertStringToSqlDate(dateOfBirth));
            prepState.setString(4, address);
            prepState.setString(5, city);
            prepState.setString(6, province);
            prepState.setString(7, postalCode);
            prepState.setString(8, phoneNumber);
            prepState.setString(9, email);
            prepState.setString(10, gender);
            prepState.setString(11, driversLicenceNumber);
            prepState.setDate(12, ConvertDates.convertStringToSqlDate(licenseDateIssued));
            prepState.setInt(13,locationCode);
            //execute query
            prepState.executeUpdate();

            driver = new PrimaryDriver(userId, firstName, lastName, ConvertDates.convertStringToUtilDate(dateOfBirth), address, city,
                    province, postalCode, phoneNumber, email, gender,
                    driversLicenceNumber, ConvertDates.convertStringToUtilDate(licenseDateIssued), locationCode);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnectionsNoResult(statement, connection);
        }
        return driver;
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

    private void closeConnectionsNoResult(Statement statement, Connection connection ){
        try {

            statement.close();
            connection.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
