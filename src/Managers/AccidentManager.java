package Managers;

import Insurance.Accident;
import Insurance.Driver;

import java.sql.*;

public class AccidentManager {

    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * insertAccident inserts an accident for the specific user. Does not return anything.
     * @param primaryID Int
     * @param secondaryID Int
     * @param yearOfAccident Int
     * @param responsible boolean
     * @throws SQLException throws sqlexception if connection or query failed.
     */
    public void insertAccident(int primaryID, int secondaryID, int yearOfAccident, Boolean responsible){

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO Accidents(UserID, SecondaryDriverID, DateOfAccident, Responsible)\n" +
                    "VALUES(?,?,?,?);";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setInt(1,primaryID);
            prepState.setInt(2,secondaryID);
            prepState.setInt(3,yearOfAccident);
            prepState.setBoolean(4, responsible);
            prepState.executeUpdate();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnectionsNoResult(statement,connection);
        }
    }


    public int countFive(int userId){
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "SELECT COUNT(accidentID) AS count FROM Accidents WHERE DateOfAccident >= YEAR(CURDATE()) - 5 AND UserID = ? AND Responsible = 1;";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setInt(1, userId);

            resultSet = prepState.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return count;
    }

    public int countTen(int userId){
        int count = 0;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "SELECT COUNT(accidentID) AS count FROM Accidents WHERE DateOfAccident >= YEAR(CURDATE()) - 10 AND DateOfAccident <= YEAR(CURDATE()) - 5 AND UserID = ? AND Responsible = 1;";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setInt(1, userId);

            resultSet = prepState.executeQuery();
            resultSet.next();
            count = resultSet.getInt("count");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return count;
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
