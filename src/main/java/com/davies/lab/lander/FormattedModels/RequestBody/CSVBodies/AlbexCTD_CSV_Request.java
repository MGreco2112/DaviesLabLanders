package com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies;

import com.opencsv.bean.CsvBindByName;

public class AlbexCTD_CSV_Request {
    @CsvBindByName(column = "Date")
    private String date;
    @CsvBindByName(column = "Salinity (PSU)")
    private Double salinity_psu;
    @CsvBindByName(column = "Temperature (C) ")
    private Double temperature_c;
    @CsvBindByName(column = "Oxygen (ml/l)")
    private Double oxygen_ml_l;
    @CsvBindByName(column = "Oxygen_Sat (%)")
    private Double oxygen_sat_percent;
    @CsvBindByName(column = "Turbidity (NTU)")
    private Double turbidity_ntu;
    @CsvBindByName(column = "Chla (ug/ml)")
    private Double chla_ug_ml;
    @CsvBindByName(column = "Pressure (db)")
    private Double pressure_db;
    @CsvBindByName(column = "Flag")
    private Integer flag;

    public AlbexCTD_CSV_Request() {
    }

    public AlbexCTD_CSV_Request(String date, Double salinity_psu, Double temperature_c, Double oxygen_ml_l, Double oxygen_sat_percent, Double turbidity_ntu, Double chla_ug_ml, Double pressure_db, Integer flag) {
        this.date = date;
        this.salinity_psu = salinity_psu;
        this.temperature_c = temperature_c;
        this.oxygen_ml_l = oxygen_ml_l;
        this.oxygen_sat_percent = oxygen_sat_percent;
        this.turbidity_ntu = turbidity_ntu;
        this.chla_ug_ml = chla_ug_ml;
        this.pressure_db = pressure_db;
        this.flag = flag;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getSalinity_psu() {
        return salinity_psu;
    }

    public void setSalinity_psu(Double salinity_psu) {
        this.salinity_psu = salinity_psu;
    }

    public Double getTemperature_c() {
        return temperature_c;
    }

    public void setTemperature_c(Double temperature_c) {
        this.temperature_c = temperature_c;
    }

    public Double getOxygen_ml_l() {
        return oxygen_ml_l;
    }

    public void setOxygen_ml_l(Double oxygen_ml_l) {
        this.oxygen_ml_l = oxygen_ml_l;
    }

    public Double getOxygen_sat_percent() {
        return oxygen_sat_percent;
    }

    public void setOxygen_sat_percent(Double oxygen_sat_percent) {
        this.oxygen_sat_percent = oxygen_sat_percent;
    }

    public Double getTurbidity_ntu() {
        return turbidity_ntu;
    }

    public void setTurbidity_ntu(Double turbidity_ntu) {
        this.turbidity_ntu = turbidity_ntu;
    }

    public Double getChla_ug_ml() {
        return chla_ug_ml;
    }

    public void setChla_ug_ml(Double chla_ug_ml) {
        this.chla_ug_ml = chla_ug_ml;
    }

    public Double getPressure_db() {
        return pressure_db;
    }

    public void setPressure_db(Double pressure_db) {
        this.pressure_db = pressure_db;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
