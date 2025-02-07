package com.davies.lab.lander.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProcessedCTDData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String Date;
    private Double TempDegC;
    private Double Sal;
    private Double CondMsCm;
    private Double Ec25UsCm;
    private Double BattV;
    //TODO: Connect to CTD Head Entity
    private Integer HeadID;

    public ProcessedCTDData() {
    }

    public ProcessedCTDData(Integer ID, String date, Double tempDegC, Double sal, Double condMsCm, Double ec25UsCm, Double battV, Integer headID) {
        this.ID = ID;
        Date = date;
        TempDegC = tempDegC;
        Sal = sal;
        CondMsCm = condMsCm;
        Ec25UsCm = ec25UsCm;
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

    public Double getSal() {
        return Sal;
    }

    public void setSal(Double sal) {
        Sal = sal;
    }

    public Double getCondMsCm() {
        return CondMsCm;
    }

    public void setCondMsCm(Double condMsCm) {
        CondMsCm = condMsCm;
    }

    public Double getEc25UsCm() {
        return Ec25UsCm;
    }

    public void setEc25UsCm(Double ec25UsCm) {
        Ec25UsCm = ec25UsCm;
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
