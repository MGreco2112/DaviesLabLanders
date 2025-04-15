package com.davies.lab.lander.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProcessedFLNTUData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private LocalDateTime Date;
    private Double TempDegC;
    private Double ChlFluPPB;
    private Double ChlAUgL;
    private Double TurbMFTU;
    private Double BattV;
    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedFLNTUHead HeadID;

    public ProcessedFLNTUData() {
    }

    public ProcessedFLNTUData(LocalDateTime date, Double tempDegC, Double chlFluPPB, Double chlAUgL, Double turbMFTU, Double battV, ProcessedFLNTUHead headID) {
        Date = date;
        TempDegC = tempDegC;
        ChlFluPPB = chlFluPPB;
        ChlAUgL = chlAUgL;
        TurbMFTU = turbMFTU;
        BattV = battV;
        HeadID = headID;
    }

    public ProcessedFLNTUData(Long ID, LocalDateTime date, Double tempDegC, Double chlFluPPB, Double chlAUgL, Double turbMFTU, Double battV, ProcessedFLNTUHead headID) {
        this.ID = ID;
        Date = date;
        TempDegC = tempDegC;
        ChlFluPPB = chlFluPPB;
        ChlAUgL = chlAUgL;
        TurbMFTU = turbMFTU;
        BattV = battV;
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

    public ProcessedFLNTUHead getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedFLNTUHead headID) {
        HeadID = headID;
    }
}
