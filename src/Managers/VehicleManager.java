package Managers;

import Conversions.ConvertDates;
import Insurance.Home;
import Insurance.PrimaryDriver;
import Insurance.Vehicle;

import java.sql.*;

public class VehicleManager {


    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;


    public Vehicle insertVehicle(double vehicleValue, String make, int year, String model, String plateNumber) {

        Vehicle vehicle = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            //InsertQuote
            statement = connection.createStatement();
            String query = "INSERT INTO Vehicle(Make, Year, Model, PlateNumber)\n" +
                    "VALUES(?,?,?,?);";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setString(1,make);
            prepState.setInt(2,year);
            prepState.setString(3,model);
            prepState.setString(4, plateNumber);
            prepState.executeUpdate();
            //create driver object

            query = "SELECT * FROM Vehicle WHERE VehicleID = (SELECT MAX(VehicleID) FROM vehicle)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();
            vehicle = new Vehicle(resultSet.getInt("VehicleID"), vehicleValue, make, year, model, plateNumber);

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return vehicle;
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
