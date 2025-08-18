package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.Data.ProcessedCTDData;

import java.time.LocalDateTime;

public class AlignedCTDDataResponse {
    private Long ID;
    private LocalDateTime date;
    private Double Temperature_C;
    private Double Oxygen_ML_L;
    private Double Oxygen_Sat_Percent;
    private Double Turbidity_NTU;
    private Double Chla_ug_mL;
    private Double Pressure;
    private Integer CTD_Flag;

    public AlignedCTDDataResponse() {
    }

    public AlignedCTDDataResponse(Long ID, LocalDateTime date, Double temperature_C, Double oxygen_ML_L, Double oxygen_Sat_Percent, Double turbidity_NTU, Double chla_ug_mL, Double pressure, Integer CTD_Flag) {
        this.ID = ID;
        this.date = date;
        Temperature_C = temperature_C;
        Oxygen_ML_L = oxygen_ML_L;
        Oxygen_Sat_Percent = oxygen_Sat_Percent;
        Turbidity_NTU = turbidity_NTU;
        Chla_ug_mL = chla_ug_mL;
        Pressure = pressure;
        this.CTD_Flag = CTD_Flag;
    }

    public AlignedCTDDataResponse(ProcessedCTDData processedData) {
        this.ID = processedData.getID();
        this.date = processedData.getDate();
        Temperature_C = processedData.getAlignedData().getTemperature_C();
        Oxygen_ML_L = processedData.getAlignedData().getOxygen_ML_L();
        Oxygen_Sat_Percent = processedData.getAlignedData().getOxygen_Sat_Percent();
        Turbidity_NTU = processedData.getAlignedData().getTurbidity_NTU();
        Chla_ug_mL = processedData.getAlignedData().getChla_ug_mL();
        Pressure = processedData.getAlignedData().getPressure();
        this.CTD_Flag = processedData.getAlignedData().getCTD_Flag();
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
}
