package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.Headers.ProcessedFLNTUHead;

import java.time.LocalDateTime;

public class UpdateFLNTUDataRequest {
    private LocalDateTime Date;
    private Double TempDegC;
    private Double ChlFluPPB;
    private Double ChlAUgL;
    private Double TurbMFTU;
    private Double BattV;
    private Boolean isAligned;
    private ProcessedFLNTUHead HeadID;

    public UpdateFLNTUDataRequest() {
    }

    public UpdateFLNTUDataRequest(LocalDateTime date, Double tempDegC, Double chlFluPPB, Double chlAUgL, Double turbMFTU, Double battV, Boolean isAligned, ProcessedFLNTUHead headID) {
        Date = date;
        TempDegC = tempDegC;
        ChlFluPPB = chlFluPPB;
        ChlAUgL = chlAUgL;
        TurbMFTU = turbMFTU;
        BattV = battV;
        this.isAligned = isAligned;
        HeadID = headID;
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
