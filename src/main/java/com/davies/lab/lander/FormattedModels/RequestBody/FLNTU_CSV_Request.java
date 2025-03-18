package com.davies.lab.lander.FormattedModels.RequestBody;

import com.opencsv.bean.CsvBindByName;

public class FLNTU_CSV_Request {
    @CsvBindByName(column = "Date")
    private String date;
    @CsvBindByName(column = "Chl-Flu.[ppb]")
    private Double chlFluPpb;
    @CsvBindByName(column = "Chl-a[ug/l]")
    private Double chlAUgL;
    @CsvBindByName(column = "Turb. -M[FTU]")
    private Double turbMFtu;
    @CsvBindByName(column = "Batt.[V]")
    private Double battV;

    public FLNTU_CSV_Request() {
    }

    public FLNTU_CSV_Request(String date, Double chlFluPpb, Double chlAUgL, Double turbMFtu, Double battV) {
        this.date = date;
        this.chlFluPpb = chlFluPpb;
        this.chlAUgL = chlAUgL;
        this.turbMFtu = turbMFtu;
        this.battV = battV;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getChlFluPpb() {
        return chlFluPpb;
    }

    public void setChlFluPpb(Double chlFluPpb) {
        this.chlFluPpb = chlFluPpb;
    }

    public Double getChlAUgL() {
        return chlAUgL;
    }

    public void setChlAUgL(Double chlAUgL) {
        this.chlAUgL = chlAUgL;
    }

    public Double getTurbMFtu() {
        return turbMFtu;
    }

    public void setTurbMFtu(Double turbMFtu) {
        this.turbMFtu = turbMFtu;
    }

    public Double getBattV() {
        return battV;
    }

    public void setBattV(Double battV) {
        this.battV = battV;
    }
}
