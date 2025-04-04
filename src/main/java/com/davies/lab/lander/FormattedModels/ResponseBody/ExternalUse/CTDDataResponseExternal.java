package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedCTDData;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CTDDataResponseExternal {
    private Integer ID;
    private LocalDateTime date;
    private Double TempDegC;
    private Double Sal;
    private Double CondMsCm;
    private Double Ec25UsCm;
    private Double BattV;
    private Integer HeadID;

    public CTDDataResponseExternal() {
    }

    public CTDDataResponseExternal (ProcessedCTDData data) {
        ID = data.getID();
        date = data.getDate();
        TempDegC = data.getTempDegC();
        Sal = data.getSal();
        CondMsCm = data.getCondMsCm();
        Ec25UsCm = data.getEc25UsCm();
        BattV = data.getBattV();
        HeadID = data.getHeadID().getHeadID();
    }

    public static Set<CTDDataResponseExternal> createBulkResponses(List<ProcessedCTDData> headData) {
        Set<CTDDataResponseExternal> newSet = new HashSet<>();

        for (ProcessedCTDData data : headData) {
            newSet.add(new CTDDataResponseExternal(data));
        }

        return newSet;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
