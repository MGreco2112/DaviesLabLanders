package com.davies.lab.lander.Models.Data.Aligned;

import com.davies.lab.lander.FormattedModels.RequestBody.AlignedCTDRequest;
import com.davies.lab.lander.Models.Data.ProcessedCTDData;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AlignedCTDData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Double Salinity_PSU;
    private Double Temperature_C;
    private Double Oxygen_ML_L;
    private Double Oxygen_Sat_Percent;
    private Double Turbidity_NTU;
    private Double Chla_ug_mL;
    private Double Pressure;
    private Integer CTD_Flag;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "raw_data", referencedColumnName = "ID")
    @JsonIgnore
    private ProcessedCTDData rawData;

    public AlignedCTDData() {
    }

    public AlignedCTDData(Double salinity_PSU, Double temperature_C, Double oxygen_ML_L, Double oxygen_Sat_Percent, Double turbidity_NTU, Double chla_ug_mL, Double pressure, Integer CTD_Flag) {
        Salinity_PSU = salinity_PSU;
        Temperature_C = temperature_C;
        Oxygen_ML_L = oxygen_ML_L;
        Oxygen_Sat_Percent = oxygen_Sat_Percent;
        Turbidity_NTU = turbidity_NTU;
        Chla_ug_mL = chla_ug_mL;
        Pressure = pressure;
        this.CTD_Flag = CTD_Flag;
    }

    public AlignedCTDData(Long ID, Double salinity_PSU, Double temperature_C, Double oxygen_ML_L, Double oxygen_Sat_Percent, Double turbidity_NTU, Double chla_ug_mL, Double pressure, Integer CTD_Flag, ProcessedCTDData rawData) {
        this.ID = ID;
        Salinity_PSU = salinity_PSU;
        Temperature_C = temperature_C;
        Oxygen_ML_L = oxygen_ML_L;
        Oxygen_Sat_Percent = oxygen_Sat_Percent;
        Turbidity_NTU = turbidity_NTU;
        Chla_ug_mL = chla_ug_mL;
        Pressure = pressure;
        this.CTD_Flag = CTD_Flag;
        this.rawData = rawData;
    }

    public AlignedCTDData(AlignedCTDRequest request) {
        Salinity_PSU = request.getSalinity_PSU();
        Temperature_C = request.getTemperature_C();
        Oxygen_ML_L = request.getOxygen_ML_L();
        Oxygen_Sat_Percent = request.getOxygen_Sat_Percent();
        Turbidity_NTU = request.getTurbidity_NTU();
        Chla_ug_mL = request.getChla_ug_mL();
        Pressure = request.getPressure();
        this.CTD_Flag = request.getCTD_Flag();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public ProcessedCTDData getRawData() {
        return rawData;
    }

    public void setRawData(ProcessedCTDData rawData) {
        this.rawData = rawData;
    }
}