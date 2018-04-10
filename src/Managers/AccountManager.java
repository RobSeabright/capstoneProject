package Managers;


import Insurance.Account;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.ArrayList;

public class AccountManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * createNewAccount creates a new Account in the database and returns the java object Account
     * @param email String
     * @param password String
     * @return returns Account object
     * @throws SQLException throws sqlException if queries or database connection were not successfully ran
     */
    public Account createNewAccount(String email, String password) {
        Account account = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO Account(Email, Password)\n" +
                    "VALUES(?, ?);";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setString(1, email);
            prepState.setString(2, password);
            prepState.executeUpdate();
            //Get created quote
            query = "SELECT * FROM Account WHERE UserID = (SELECT MAX(UserID) FROM Account)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();
            account =  new Account(resultSet.getInt("UserID"), email, password);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return account;
    }
    public ArrayList<Object> accountLogin(String email, String password){
        ArrayList<Object> list = new ArrayList<>();
        Boolean access = false;
        Account account = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "SELECT * FROM Account WHERE Email = ?";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setString(1, email);
            resultSet = prepState.executeQuery();
            resultSet.next();
            //Only if passwords match, update the values.
            if(password.equals(resultSet.getString("Password"))){
                account =  new Account(resultSet.getInt("UserID"), email, password);
                access = true;
            }
            //add objects to the list.
            list.add(access);
            list.add(account);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }

        return list;
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
