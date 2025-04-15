package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedFLNTUData;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FLNTUDataResponseExternal {
    private Long ID;
    private LocalDateTime Date;
    private Double TempDegC;
    private Double ChlFluPPB;
    private Double ChlAUgL;
    private Double TurbMFTU;
    private Double BattV;
    private Long HeadID;

    public FLNTUDataResponseExternal() {
    }

    public FLNTUDataResponseExternal(ProcessedFLNTUData data) {
        ID = data.getID();
        Date = data.getDate();
        TempDegC = data.getTempDegC();
        ChlFluPPB = data.getChlFluPPB();
        ChlAUgL = data.getChlAUgL();
        TurbMFTU = data.getTurbMFTU();
        BattV = data.getBattV();
        HeadID = data.getHeadID().getHeadID();
    }

    public static Set<FLNTUDataResponseExternal> createDataResponse(List<ProcessedFLNTUData> data) {
        Set<FLNTUDataResponseExternal> responseSet = new HashSet<>();

        for (ProcessedFLNTUData dataPoint : data) {
            responseSet.add(new FLNTUDataResponseExternal(dataPoint));
        }

        return responseSet;
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

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }
}
