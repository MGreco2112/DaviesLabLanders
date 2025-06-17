package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedDOData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DODataResponseExternal {
    private Long ID;
    private LocalDateTime Date;
    private Double TempDegC;
    private Double DO;
    private Double WeissDoMgL;
    private Double BattV;
    private Double GGDOMgL;
    private Double BKDOMgL;
    private Long HeadID;

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

    public static List<DODataResponseExternal> createDataResponses(List<ProcessedDOData> data) {
        List<DODataResponseExternal> responseList = new ArrayList<>();

        for (ProcessedDOData dataPoint : data) {
            responseList.add(new DODataResponseExternal(dataPoint));
        }

        return responseList;
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

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }
}
