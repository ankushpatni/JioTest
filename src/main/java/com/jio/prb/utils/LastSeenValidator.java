package com.jio.prb.utils;

import com.jio.prb.exception.LastSeenException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LastSeenValidator {

    public static boolean checkdateFormat(String dateTime)
    {
        try {
            DateFormat df = new SimpleDateFormat(LastSeenConstants.DATE_FORMAT);
            df.setLenient(false);

            df.parse(dateTime); // exception is not thrown if day and month is
            // correct AND the first char of year is a digit

            // so if we have correct day and correct month
            // and we know the year has 4 chars we can try to parse it
            Integer year = Integer.parseInt(dateTime.substring(6, 10));

            if (year>= 1900 && year<=2025) // here we know that the year is 4 digit integer
                return true;               // and we can limit it
            else
                return false;

        } catch (ParseException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean checkFutureDate(String dateTime)
    {
        try {
            DateFormat df = new SimpleDateFormat(LastSeenConstants.DATE_FORMAT);
            df.setLenient(false);

            df.parse(dateTime);
            long now = System.currentTimeMillis();
            if (df.parse(dateTime).getTime()<= now) // here we know that the year is 4 digit integer
                return true;               // and we can limit it
            else
                return false;
        } catch (ParseException e) {
            return false;
        } catch (NumberFormatException e) {
            return false;
        }
    }


}
