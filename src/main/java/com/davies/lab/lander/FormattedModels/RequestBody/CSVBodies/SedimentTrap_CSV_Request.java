package com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies;

import com.opencsv.bean.CsvBindByName;

public class SedimentTrap_CSV_Request {
    @CsvBindByName(column = "Date")
    private String date;

    public SedimentTrap_CSV_Request() {
    }

    public SedimentTrap_CSV_Request(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
