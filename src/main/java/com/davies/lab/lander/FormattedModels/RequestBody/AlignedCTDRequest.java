package com.davies.lab.lander.FormattedModels.RequestBody;

public class AlignedCTDRequest {
    private Long rawID;
    private Double Salinity_PSU;
    private Double Temperature_C;
    private Double Oxygen_ML_L;
    private Double Oxygen_Sat_Percent;
    private Double Turbidity_NTU;
    private Double Chla_ug_mL;
    private Double Pressure;
    private Integer CTD_Flag;

    public AlignedCTDRequest() {
    }

    public AlignedCTDRequest(Long rawID, Double salinity_PSU, Double temperature_C, Double oxygen_ML_L, Double oxygen_Sat_Percent, Double turbidity_NTU, Double chla_ug_mL, Double pressure, Integer CTD_Flag) {
        this.rawID = rawID;
        Salinity_PSU = salinity_PSU;
        Temperature_C = temperature_C;
        Oxygen_ML_L = oxygen_ML_L;
        Oxygen_Sat_Percent = oxygen_Sat_Percent;
        Turbidity_NTU = turbidity_NTU;
        Chla_ug_mL = chla_ug_mL;
        Pressure = pressure;
        this.CTD_Flag = CTD_Flag;
    }

    public Long getRawID() {
        return rawID;
    }

    public void setRawID(Long rawID) {
        this.rawID = rawID;
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
}
