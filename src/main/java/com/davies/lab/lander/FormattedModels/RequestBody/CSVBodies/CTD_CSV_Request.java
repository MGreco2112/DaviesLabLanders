package com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies;

import com.opencsv.bean.CsvBindByName;

public class CTD_CSV_Request {

    @CsvBindByName(column = "Date")
    private String date;

    @CsvBindByName(column = "Temp.[deg C]")
    private Double tempDegC;

    @CsvBindByName(column = "Sal.[ ]")
    private Double sal;

    @CsvBindByName(column = "Cond.[mS/cm]")
    private Double condMsCm;

    @CsvBindByName(column = "EC 25[uS/cm]")
    private Double eC25uScM;

    @CsvBindByName(column = "Batt.[V]")
    private Double battV;

    public CTD_CSV_Request() {
    }

    public CTD_CSV_Request(String date, Double tempDegC, Double sal, Double condMsCm, Double eC25uScM, Double battV) {
        this.date = date;
        this.tempDegC = tempDegC;
        this.sal = sal;
        this.condMsCm = condMsCm;
        this.eC25uScM = eC25uScM;
        this.battV = battV;
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

    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    public Double getCondMsCm() {
        return condMsCm;
    }

    public void setCondMsCm(Double condMsCm) {
        this.condMsCm = condMsCm;
    }

    public Double geteC25uScM() {
        return eC25uScM;
    }

    public void seteC25uScM(Double eC25uScM) {
        this.eC25uScM = eC25uScM;
    }

    public Double getBattV() {
        return battV;
    }

    public void setBattV(Double battV) {
        this.battV = battV;
    }

    @Override
    public String toString() {
        return "{ \"date\": \"" + date + "\", \"tempDegC\": " + tempDegC + ", \"sal\": " + sal + ", \"condMsCm\": " + condMsCm + ", \"eC25uScM\": " + eC25uScM + ", \"battV\": " + battV + "}";
    }
}
