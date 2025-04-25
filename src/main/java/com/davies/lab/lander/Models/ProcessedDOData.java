package com.davies.lab.lander.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProcessedDOData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private LocalDateTime Date;
    @Column(nullable = false)
    private Double TempDegC;
    @Column(nullable = false)
    private Double DO;
    @Column(nullable = false)
    private Double WeissDoMgL;
    @Column(nullable = false)
    private Double BattV;
    @Column(nullable = false)
    private Double GGDOMgL;
    @Column(nullable = false)
    private Double BKDOMgL;
    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedDOHead HeadID;

    public ProcessedDOData() {
    }

    public ProcessedDOData(LocalDateTime date, Double tempDegC, Double DO, Double weissDoMgL, Double battV, Double GGDOMgL, Double BKDOMgL, ProcessedDOHead headID) {
        Date = date;
        TempDegC = tempDegC;
        this.DO = DO;
        WeissDoMgL = weissDoMgL;
        BattV = battV;
        this.GGDOMgL = GGDOMgL;
        this.BKDOMgL = BKDOMgL;
        HeadID = headID;
    }

    public ProcessedDOData(Long ID, LocalDateTime date, Double tempDegC, Double DO, Double weissDoMgL, Double battV, Double GGDOMgL, Double BKDOMgL, ProcessedDOHead headID) {
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

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
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
