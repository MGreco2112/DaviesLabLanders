package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;

public class UpdateCameraDataRequest {
    private ProcessedCameraHeader HeadID;

    public UpdateCameraDataRequest() {
    }

    public UpdateCameraDataRequest(ProcessedCameraHeader headID) {
        HeadID = headID;
    }

    public ProcessedCameraHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedCameraHeader headID) {
        HeadID = headID;
    }
}
