package cahyo.batch5.util;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class DateConversion {
    public static Date convertToSqlDate(java.util.Date date) {
        return new Date(date.getTime());
    }

    public static java.util.Date convertToUtilDate(Date date) {
        return new java.util.Date(date.getTime());
    }

    public static java.util.Date deserializeToDate(String date) {
        try {
            // default format from Date is like -> Thu Apr 18 07:00:00 ICT 1996
            DateFormat dateFormat = new SimpleDateFormat("EEE MMM dd hh:mm:ss zzz yyy");

            return dateFormat.parse(date);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
