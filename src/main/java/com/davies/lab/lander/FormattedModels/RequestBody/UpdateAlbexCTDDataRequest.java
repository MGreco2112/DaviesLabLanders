package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;

import java.time.LocalDateTime;

public class UpdateAlbexCTDDataRequest {
    private LocalDateTime Date;
    private Double Salinity;
    private Double Temperature;
    private Double Oxygen_ml_l;
    private Double OxygenSat_percent;
    private Double Turbidity_ntu;
    private Double Chla_ug_ml;
    private Double Pressure_db;
    private Integer Flag;
    private Boolean isAligned;
    private ProcessedAlbexCTDHeader HeadID;

    public UpdateAlbexCTDDataRequest() {
    }

    public UpdateAlbexCTDDataRequest(LocalDateTime date, Double salinity, Double temperature, Double oxygen_ml_l, Double oxygenSat_percent, Double turbidity_ntu, Double chla_ug_ml, Double pressure_db, Integer flag, Boolean isAligned, ProcessedAlbexCTDHeader headID) {
        Date = date;
        Salinity = salinity;
        Temperature = temperature;
        Oxygen_ml_l = oxygen_ml_l;
        OxygenSat_percent = oxygenSat_percent;
        Turbidity_ntu = turbidity_ntu;
        Chla_ug_ml = chla_ug_ml;
        Pressure_db = pressure_db;
        Flag = flag;
        this.isAligned = isAligned;
        HeadID = headID;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public Double getSalinity() {
        return Salinity;
    }

    public void setSalinity(Double salinity) {
        Salinity = salinity;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getOxygen_ml_l() {
        return Oxygen_ml_l;
    }

    public void setOxygen_ml_l(Double oxygen_ml_l) {
        Oxygen_ml_l = oxygen_ml_l;
    }

    public Double getOxygenSat_percent() {
        return OxygenSat_percent;
    }

    public void setOxygenSat_percent(Double oxygenSat_percent) {
        OxygenSat_percent = oxygenSat_percent;
    }

    public Double getTurbidity_ntu() {
        return Turbidity_ntu;
    }

    public void setTurbidity_ntu(Double turbidity_ntu) {
        Turbidity_ntu = turbidity_ntu;
    }

    public Double getChla_ug_ml() {
        return Chla_ug_ml;
    }

    public void setChla_ug_ml(Double chla_ug_ml) {
        Chla_ug_ml = chla_ug_ml;
    }

    public Double getPressure_db() {
        return Pressure_db;
    }

    public void setPressure_db(Double pressure_db) {
        Pressure_db = pressure_db;
    }

    public Integer getFlag() {
        return Flag;
    }

    public void setFlag(Integer flag) {
        Flag = flag;
    }

    public Boolean getAligned() {
        return isAligned;
    }

    public void setAligned(Boolean aligned) {
        isAligned = aligned;
    }

    public ProcessedAlbexCTDHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedAlbexCTDHeader headID) {
        HeadID = headID;
    }
}
