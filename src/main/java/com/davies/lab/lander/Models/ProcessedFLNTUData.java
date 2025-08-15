package com.davies.lab.lander.Models;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.FLNTU_CSV_Request;
import com.davies.lab.lander.HelperClasses.StringFormatting;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ProcessedFLNTUData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private LocalDateTime Date;
    @Column(nullable = false)
    private Double TempDegC;
    @Column(nullable = false)
    private Double ChlFluPPB;
    @Column(nullable = false)
    private Double ChlAUgL;
    @Column(nullable = false)
    private Double TurbMFTU;
    @Column(nullable = false)
    private Double BattV;
    private Boolean isAligned;
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
        this.isAligned = false;
        HeadID = headID;
    }

    public ProcessedFLNTUData(LocalDateTime date, Double tempDegC, Double chlFluPPB, Double chlAUgL, Double turbMFTU, Double battV, Boolean isAligned, ProcessedFLNTUHead headID) {
        Date = date;
        TempDegC = tempDegC;
        ChlFluPPB = chlFluPPB;
        ChlAUgL = chlAUgL;
        TurbMFTU = turbMFTU;
        BattV = battV;
        this.isAligned = isAligned;
        HeadID = headID;
    }

    public ProcessedFLNTUData(FLNTU_CSV_Request dataElement, ProcessedFLNTUHead head) {
        Date = StringFormatting.formatDateString(dataElement.getDate());
        TempDegC = dataElement.getTempDegC();
        ChlFluPPB = dataElement.getChlFluPpb();
        ChlAUgL = dataElement.getChlAUgL();
        TurbMFTU = dataElement.getTurbMFtu();
        BattV = dataElement.getBattV();
        isAligned = false;
        HeadID = head;
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

    public Boolean getAligned() {
        return isAligned;
    }

    public void setAligned(Boolean aligned) {
        isAligned = aligned;
    }

    public ProcessedFLNTUHead getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedFLNTUHead headID) {
        HeadID = headID;
    }
}
