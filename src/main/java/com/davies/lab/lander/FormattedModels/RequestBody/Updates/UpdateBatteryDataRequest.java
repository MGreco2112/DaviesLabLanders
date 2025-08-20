package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;

public class UpdateBatteryDataRequest {
    private ProcessedBatteryHeader HeadID;

    public UpdateBatteryDataRequest() {
    }

    public UpdateBatteryDataRequest(ProcessedBatteryHeader headID) {
        HeadID = headID;
    }

    public ProcessedBatteryHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedBatteryHeader headID) {
        HeadID = headID;
    }
}
