package com.davies.lab.lander.FormattedModels.ResponseBody.Data;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;

public class BatteryDataResponse {
    private Long ID;
    private Long HeadID;

    public BatteryDataResponse(Long ID, Long headID) {
        this.ID = ID;
        HeadID = headID;
    }

    public BatteryDataResponse(ProcessedBatteryData processedData) {
        this.ID = processedData.getId();
        this.HeadID = processedData.getHeadID().getHeadID();
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
