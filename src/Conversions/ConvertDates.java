package Conversions;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class that holds functions to convert javaDates and SqlDates to one another
 */
public class ConvertDates {

    /**
     * Convert java Date to sql date
     * @param uDate
     * @return
     */
   public static java.sql.Date convertToSqlDate(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }

    /**
     * Convert sql date to java date
     * @param sDate
     * @return
     */
    public static java.util.Date convertToUtilDate(java.sql.Date sDate){
        java.util.Date uDate = null;
        if (sDate != null) {
            uDate = new Date(sDate.getTime());
        }
        return uDate;
    }

    public static java.sql.Date convertStringToSqlDate(String date){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = sdf1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        java.sql.Date sqlStartDate = new java.sql.Date(newDate.getTime());

        return sqlStartDate;
    }


    public static java.util.Date convertStringToUtilDate(String date){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = null;
        try {
            newDate = sdf1.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return newDate;
    }

    /**
     * Converts a date to be rendered on the website.
     * @param date
     * @return
     */
    public static String convertToDisplayDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MMM-dd");

        return format.format(date);
    }
}
