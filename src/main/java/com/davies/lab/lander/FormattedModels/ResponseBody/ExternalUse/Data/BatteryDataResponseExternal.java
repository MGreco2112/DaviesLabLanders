package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Data;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;

import java.util.ArrayList;
import java.util.List;

public class BatteryDataResponseExternal {
    private Long ID;
    private Long HeadID;

    public BatteryDataResponseExternal() {
    }

    public BatteryDataResponseExternal(ProcessedBatteryData data) {
        ID = data.getId();
        HeadID = data.getHeadID().getHeadID();
    }

    public static List<BatteryDataResponseExternal> createDataResponse(List<ProcessedBatteryData> data) {
        List<BatteryDataResponseExternal> responseList = new ArrayList<>();

        for (ProcessedBatteryData dataPoint : data) {
            responseList.add(new BatteryDataResponseExternal(dataPoint));

        }

        return responseList;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }
}
