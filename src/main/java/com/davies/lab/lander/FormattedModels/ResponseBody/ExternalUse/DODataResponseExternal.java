package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedDOData;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DODataResponseExternal {
    private Integer ID;
    private LocalDateTime Date;
    private Double TempDegC;
    private Double DO;
    private Double WeissDoMgL;
    private Double BattV;
    private Double GGDOMgL;
    private Double BKDOMgL;
    private Integer HeadID;

    public DODataResponseExternal() {
    }

    public DODataResponseExternal(ProcessedDOData data) {
        ID = data.getID();
        Date = data.getDate();
        TempDegC = data.getTempDegC();
        DO = data.getDO();
        WeissDoMgL = data.getWeissDoMgL();
        BattV = data.getBattV();
        GGDOMgL = data.getGGDOMgL();
        BKDOMgL = data.getBKDOMgL();
        HeadID = data.getHeadID().getHeadID();
    }

    public static Set<DODataResponseExternal> createDataResponses(List<ProcessedDOData> data) {
        Set<DODataResponseExternal> responseSet = new HashSet<>();

        for (ProcessedDOData dataPoint : data) {
            responseSet.add(new DODataResponseExternal(dataPoint));
        }

        return responseSet;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
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

    public Integer getHeadID() {
        return HeadID;
    }

    public void setHeadID(Integer headID) {
        HeadID = headID;
    }
}
