package com.davies.lab.lander.FormattedModels.ResponseBody.Data;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;

public class BeaconDataResponse {
    private Long ID;
    private Long HeadID;

    public BeaconDataResponse(Long ID, Long headID) {
        this.ID = ID;
        HeadID = headID;
    }

    public BeaconDataResponse(ProcessedBeaconData data) {
        ID = data.getID();
        HeadID = data.getHeadID().getHeadID();
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
