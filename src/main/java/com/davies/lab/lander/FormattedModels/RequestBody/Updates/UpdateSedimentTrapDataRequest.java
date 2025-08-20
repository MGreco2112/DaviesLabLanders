package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;

public class UpdateSedimentTrapDataRequest {
    private ProcessedSedimentTrapHeader HeadID;

    public UpdateSedimentTrapDataRequest() {
    }

    public UpdateSedimentTrapDataRequest(ProcessedSedimentTrapHeader headID) {
        HeadID = headID;
    }

    public ProcessedSedimentTrapHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedSedimentTrapHeader headID) {
        HeadID = headID;
    }
}
