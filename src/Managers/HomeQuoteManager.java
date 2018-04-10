package Managers;

import Conversions.ConvertDates;
import Insurance.Home;
import Insurance.HomeOwner;
import Insurance.HomeQuote;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class HomeQuoteManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * createNewQuote takes in all variables that are needed to generate a quote, inserts it to the database,
     * gets the generated ID of the homeQuote, and returns the quote as an object.
     * @param basePremium base premium double
     * @param tax tax double
     * @param total total double
     * @param home home object
     * @param homeOwner homeOwner object
     * @return returns quote object
     */
    public HomeQuote createNewQuote(double basePremium, double tax, double total, Home home, HomeOwner homeOwner) {
        HomeQuote quote =  new HomeQuote("", homeOwner, null, null, basePremium, tax, total, home, home.getValue(), 0,0, 0);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO HomeQuotes(DateQuoted, QuoteExpired, BasePremium, Tax, Total, UserID, HomeID)\n" +
                    "VALUES(CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), ?, ?, ?, ?, ?)";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setDouble(1, basePremium);
            prepState.setDouble(2, tax);
            prepState.setDouble(3, total);
            prepState.setInt(4,homeOwner.getUserId());
            prepState.setInt(5,home.getHomeID());
            prepState.executeUpdate();
            //Get created quote
            query = "SELECT * FROM HomeQuotes WHERE QuoteID = (SELECT MAX(QuoteID) FROM HomeQuotes)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();
            quote.setQuoteID(resultSet.getString("QuoteID"));
            quote.setBasePremium(resultSet.getDouble("BasePremium"));
            quote.setTax(resultSet.getDouble("Tax"));
            quote.setTotal(resultSet.getDouble("Total"));
            quote.setStartDate(ConvertDates.convertToUtilDate(resultSet.getDate("DateQuoted")));
            quote.setEndDate(ConvertDates.convertToUtilDate(resultSet.getDate("QuoteExpired")));
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
     * selectHomeQuote selects a homequote based on the quoteID and returns a HomeQuote object.
     * @param quoteID ID of quote
     * @param home homeID
     * @param homeOwner homeowner object
     * @return
     */
    public HomeQuote selectQuote(String quoteID, Home home, HomeOwner homeOwner){
        HomeQuote quote =  new HomeQuote("", homeOwner, null, null, 0, 0, 0, home, 1234, 2121,4322, 534);

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "SELECT * FROM homequotes WHERE QuoteID = ?";
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
     * @param resultSet restultSet
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
