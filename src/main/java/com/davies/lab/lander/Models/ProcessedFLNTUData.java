package com.davies.lab.lander.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProcessedFLNTUData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String Date;
    private Double TempDegC;
    private Double ChlFluPPB;
    private Double ChlAUgL;
    private Double TurbMFTU;
    private Double BattV;
    private Integer HeadID;

    public ProcessedFLNTUData() {
    }

    public ProcessedFLNTUData(Integer ID, String date, Double tempDegC, Double chlFluPPB, Double chlAUgL, Double turbMFTU, Double battV, Integer headID) {
        this.ID = ID;
        Date = date;
        TempDegC = tempDegC;
        ChlFluPPB = chlFluPPB;
        ChlAUgL = chlAUgL;
        TurbMFTU = turbMFTU;
        BattV = battV;
        HeadID = headID;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Double getTempDegC() {
        return TempDegC;
    }

    public void setTempDegC(Double tempDegC) {
        TempDegC = tempDegC;
    }

    public Double getChlFluPPB() {
        return ChlFluPPB;
    }

    public void setChlFluPPB(Double chlFluPPB) {
        ChlFluPPB = chlFluPPB;
    }

    public Double getChlAUgL() {
        return ChlAUgL;
    }

    public void setChlAUgL(Double chlAUgL) {
        ChlAUgL = chlAUgL;
    }

    public Double getTurbMFTU() {
        return TurbMFTU;
    }

    public void setTurbMFTU(Double turbMFTU) {
        TurbMFTU = turbMFTU;
    }

    public Double getBattV() {
        return BattV;
    }

    public void setBattV(Double battV) {
        BattV = battV;
    }

    public Integer getHeadID() {
        return HeadID;
    }

    public void setHeadID(Integer headID) {
        HeadID = headID;
    }
}
