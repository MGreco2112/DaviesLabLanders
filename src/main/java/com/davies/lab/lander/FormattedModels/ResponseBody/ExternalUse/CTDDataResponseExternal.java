package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Data.ProcessedCTDData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CTDDataResponseExternal {
    private Long ID;
    private LocalDateTime date;
    private Double Salinity_PSU;
    private Double Temperature_C;
    private Double Oxygen_ML_L;
    private Double Oxygen_Sat_Percent;
    private Double Turbidity_NTU;
    private Double Chla_ug_mL;
    private Double Pressure;
    private Integer CTD_Flag;
    private Long HeadID;

    public CTDDataResponseExternal() {
    }

    public CTDDataResponseExternal (ProcessedCTDData data) {
        ID = data.getID();
        date = data.getDate();
        Salinity_PSU = data.getAlignedData().getSalinity_PSU();
        Temperature_C = data.getAlignedData().getTemperature_C();
        Oxygen_ML_L = data.getAlignedData().getOxygen_ML_L();
        Oxygen_Sat_Percent = data.getAlignedData().getOxygen_Sat_Percent();
        Turbidity_NTU = data.getAlignedData().getTurbidity_NTU();
        Chla_ug_mL = data.getAlignedData().getChla_ug_mL();
        Pressure = data.getAlignedData().getPressure();
        CTD_Flag = data.getAlignedData().getCTD_Flag();
        HeadID = data.getHeadID().getHeadID();
    }

    public static List<CTDDataResponseExternal> createBulkResponses(List<ProcessedCTDData> headData) {
        List<CTDDataResponseExternal> newList = new ArrayList<>();

        for (ProcessedCTDData data : headData) {
            newList.add(new CTDDataResponseExternal(data));
        }

        return newList;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getSalinity_PSU() {
        return Salinity_PSU;
    }

    public void setSalinity_PSU(Double salinity_PSU) {
        Salinity_PSU = salinity_PSU;
    }

    public Double getTemperature_C() {
        return Temperature_C;
    }

    public void setTemperature_C(Double temperature_C) {
        Temperature_C = temperature_C;
    }

    public Double getOxygen_ML_L() {
        return Oxygen_ML_L;
    }

    public void setOxygen_ML_L(Double oxygen_ML_L) {
        Oxygen_ML_L = oxygen_ML_L;
    }

    public Double getOxygen_Sat_Percent() {
        return Oxygen_Sat_Percent;
    }

    public void setOxygen_Sat_Percent(Double oxygen_Sat_Percent) {
        Oxygen_Sat_Percent = oxygen_Sat_Percent;
    }

    public Double getTurbidity_NTU() {
        return Turbidity_NTU;
    }

    public void setTurbidity_NTU(Double turbidity_NTU) {
        Turbidity_NTU = turbidity_NTU;
    }

    public Double getChla_ug_mL() {
        return Chla_ug_mL;
    }

    public void setChla_ug_mL(Double chla_ug_mL) {
        Chla_ug_mL = chla_ug_mL;
    }

    public Double getPressure() {
        return Pressure;
    }

    public void setPressure(Double pressure) {
        Pressure = pressure;
    }

    public Integer getCTD_Flag() {
        return CTD_Flag;
    }

    public void setCTD_Flag(Integer CTD_Flag) {
        this.CTD_Flag = CTD_Flag;
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }
}
