package com.davies.lab.lander.FormattedModels.RequestBody;

import com.opencsv.bean.CsvBindByName;

public class DO_CSV_Request {
    @CsvBindByName(column = "Date")
    private String date;
    @CsvBindByName(column = "Temp.[deg C]")
    private Double tempDegC;
    @CsvBindByName(column = "DO[%]")
    private Double Do;
    @CsvBindByName(column = "Weiss-DO[mg/l]")
    private Double weissDoMgL;
    @CsvBindByName(column = "Batt.[V]")
    private Double battV;
    @CsvBindByName(column = "G&G-DO[mg/l]")
    private Double ggDoMgL;
    @CsvBindByName(column = "B&K-DO[mg/l]")
    private Double bkDoMgL;

    public DO_CSV_Request() {
    }

    public DO_CSV_Request(String date, Double tempDegC, Double aDo, Double weissDoMgL, Double battV, Double ggDoMgL, Double bkDoMgL) {
        this.date = date;
        this.tempDegC = tempDegC;
        Do = aDo;
        this.weissDoMgL = weissDoMgL;
        this.battV = battV;
        this.ggDoMgL = ggDoMgL;
        this.bkDoMgL = bkDoMgL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getTempDegC() {
        return tempDegC;
    }

    public void setTempDegC(Double tempDegC) {
        this.tempDegC = tempDegC;
    }

    public Double getDo() {
        return Do;
    }

    public void setDo(Double aDo) {
        Do = aDo;
    }

    public Double getWeissDoMgL() {
        return weissDoMgL;
    }

    public void setWeissDoMgL(Double weissDoMgL) {
        this.weissDoMgL = weissDoMgL;
    }

    public Double getBattV() {
        return battV;
    }

    public void setBattV(Double battV) {
        this.battV = battV;
    }

    public Double getGgDoMgL() {
        return ggDoMgL;
    }

    public void setGgDoMgL(Double ggDoMgL) {
        this.ggDoMgL = ggDoMgL;
    }

    public Double getBkDoMgL() {
        return bkDoMgL;
    }

    public void setBkDoMgL(Double bkDoMgL) {
        this.bkDoMgL = bkDoMgL;
    }
}
