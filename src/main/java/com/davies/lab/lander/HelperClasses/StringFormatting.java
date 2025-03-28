package com.davies.lab.lander.HelperClasses;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Arrays;

public class StringFormatting {

    public static LocalDateTime formatDateString(String inputDate) {
        String[] dateTimeSplit = inputDate.split(" ");
        String[] dateSplit = dateTimeSplit[0].split("/");
        String[] timeSplit = dateTimeSplit[1].split(":");

        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);
        int hour = Integer.parseInt(timeSplit[0]);
        int minute = Integer.parseInt(timeSplit[1]);

        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public static LocalDateTime formatFrontendDateString (String inputDate) {
        String[] dateSplit = inputDate.split("-");
        int year = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int day = Integer.parseInt(dateSplit[2]);

        return LocalDateTime.of(year, month, day, 0, 0);
    }

    public static Date formatCoefDateString(String coefDate) {
        String[] splitDate = coefDate.split("/");

        int year = Integer.parseInt(splitDate[0]);
        int month = Integer.parseInt(splitDate[1]);
        int day = Integer.parseInt(splitDate[2]);

        return new Date(year, month, day);
    }

}
