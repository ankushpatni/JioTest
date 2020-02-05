package com.jio.prb.utils;

import com.jio.prb.exception.LastSeenException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class LastSeenUtil {

    public static long getTimeInMilliSeconds(String dateTime)
    {
        try {
            DateFormat df = new SimpleDateFormat(LastSeenConstants.DATE_FORMAT);
            df.setLenient(false);
            df.parse(dateTime);
            return  df.parse(dateTime).getTime();
        } catch (ParseException e) {
            throw new LastSeenException("Not able to convert date to given format : "+ LastSeenConstants.DATE_FORMAT);
        }
    }
}
