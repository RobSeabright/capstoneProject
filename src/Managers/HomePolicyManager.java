package Managers;

import Conversions.ConvertDates;
import Insurance.*;

import java.sql.*;
import java.util.Date;

public class HomePolicyManager {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/comp_database?autoReconnect=true&useSSL=false";
    private Connection connection = null;
    private Statement statement = null;
    private ResultSet resultSet = null;

    /**
     * insertHomePolicy takes a homeQuote and converts it to a
     * @param homeQuote homeQuote object
     * @return returns a HomePolicy object
     */
    public HomePolicy insertHomePolicy(HomeQuote homeQuote)  {

        HomePolicy homePolicy = null;
        try {
            connection = java.sql.DriverManager.getConnection(DATABASE_URL, "compUser", "compUser1");
            statement = connection.createStatement();
            //Insert policy
            String query = "INSERT INTO HomePolicy(BasePremium, Tax, Total, UserID, HomeID, QuoteID, StartDate, EndDate)" +
                    "VALUES(?,?,?,?,?,?,CURDATE(),DATE_ADD(CURDATE(), INTERVAL 1 YEAR))";
            PreparedStatement prepState = connection.prepareStatement(query);
            prepState.setDouble(1,homeQuote.getBasePremium());
            prepState.setDouble(2, homeQuote.getTax());
            prepState.setDouble(3, homeQuote.getTotal());
            prepState.setInt(4,homeQuote.getUser().getUserId());
            prepState.setInt(5,homeQuote.getHome().getHomeID());
            prepState.setString(6,homeQuote.getQuoteID());

            prepState.executeUpdate();


            query = "SELECT * FROM HomePolicy WHERE PolicyID = (SELECT MAX(PolicyID) FROM HomePolicy)";
            PreparedStatement prep = connection.prepareStatement(query);
            resultSet = prep.executeQuery();
            resultSet.next();
            homePolicy = new HomePolicy(resultSet.getString("PolicyID"), homeQuote.getUser(), homeQuote, ConvertDates.convertToUtilDate(resultSet.getDate("StartDate")),ConvertDates.convertToUtilDate(resultSet.getDate("EndDate")) , homeQuote.getBasePremium(), homeQuote.getTax(), homeQuote.getTotal(), homeQuote.getHome());

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        } finally {
            closeConnections(statement, resultSet,connection);
        }
        return homePolicy;
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
