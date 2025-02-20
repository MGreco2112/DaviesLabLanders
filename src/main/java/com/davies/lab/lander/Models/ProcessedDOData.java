package com.davies.lab.lander.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
public class ProcessedDOData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ID;
    private String Date;
    private Double TempDegC;
    private Double DO;
    private Double WeissDoMgL;
    private Double BattV;
    private Double GGDOMgL;
    private Double BKDOMgL;
    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedDOHead HeadID;

    public ProcessedDOData() {
    }

    public ProcessedDOData(Integer ID, String date, Double tempDegC, Double DO, Double weissDoMgL, Double battV, Double GGDOMgL, Double BKDOMgL, ProcessedDOHead headID) {
        this.ID = ID;
        Date = date;
        TempDegC = tempDegC;
        this.DO = DO;
        WeissDoMgL = weissDoMgL;
        BattV = battV;
        this.GGDOMgL = GGDOMgL;
        this.BKDOMgL = BKDOMgL;
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

    public Double getDO() {
        return DO;
    }

    public void setDO(Double DO) {
        this.DO = DO;
    }

    public Double getWeissDoMgL() {
        return WeissDoMgL;
    }

    public void setWeissDoMgL(Double weissDoMgL) {
        WeissDoMgL = weissDoMgL;
    }

    public Double getBattV() {
        return BattV;
    }

    public void setBattV(Double battV) {
        BattV = battV;
    }

    public Double getGGDOMgL() {
        return GGDOMgL;
    }

    public void setGGDOMgL(Double GGDOMgL) {
        this.GGDOMgL = GGDOMgL;
    }

    public Double getBKDOMgL() {
        return BKDOMgL;
    }

    public void setBKDOMgL(Double BKDOMgL) {
        this.BKDOMgL = BKDOMgL;
    }

    public ProcessedDOHead getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedDOHead headID) {
        HeadID = headID;
    }
}
