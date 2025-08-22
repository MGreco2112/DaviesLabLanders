package com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies;

import com.opencsv.bean.CsvBindByName;

public class Battery_CSV_Request {
    @CsvBindByName(column = "Date")
    private String date;

    public Battery_CSV_Request() {
    }

    public Battery_CSV_Request(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
