package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;

public class UpdateBeaconDataRequest {
    private ProcessedBeaconHeader HeadID;

    public UpdateBeaconDataRequest() {
    }

    public UpdateBeaconDataRequest(ProcessedBeaconHeader headID) {
        HeadID = headID;
    }

    public ProcessedBeaconHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedBeaconHeader headID) {
        HeadID = headID;
    }
}
