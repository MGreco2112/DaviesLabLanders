package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;

import java.util.ArrayList;
import java.util.List;

public class BeaconDataResponseExternal {
    private Long ID;
    private Long HeadID;

    public BeaconDataResponseExternal() {
    }

    public BeaconDataResponseExternal(ProcessedBeaconData data) {
        ID = data.getID();
        HeadID = data.getHeadID().getHeadID();
    }

    public static List<BeaconDataResponseExternal> createDataResponse(List<ProcessedBeaconData> data) {
        List<BeaconDataResponseExternal> responseList = new ArrayList<>();

        for (ProcessedBeaconData dataPoint : data) {
            responseList.add(new BeaconDataResponseExternal(dataPoint));
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
