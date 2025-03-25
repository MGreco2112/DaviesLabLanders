package com.davies.lab.lander;

import java.time.LocalDateTime;

public class DateStringTesting {

    public static void main(String[] args) {
        String testDate = "9/14/2023 14:00";

        String[] dateTimeSplit = testDate.split(" ");

        String[] dateSplit = dateTimeSplit[0].split("/");
        String[] timeSplit = dateTimeSplit[1].split(":");

        int month = Integer.parseInt(dateSplit[0]);
        int day = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        int hour = Integer.parseInt(timeSplit[0]);
        int minute = Integer.parseInt(timeSplit[1]);

        //int year, month, day, hour, minute



        LocalDateTime formattedDate = LocalDateTime.of(year, month, day, hour, minute);

        System.out.println(formattedDate);
    }
}
