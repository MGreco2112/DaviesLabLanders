package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import com.davies.lab.lander.Models.Lander;

import java.util.List;

public class UpdateSedimentTrapHeaderRequest {
    private Lander LanderID;
    private List<ProcessedSedimentTrapData> data;

    public UpdateSedimentTrapHeaderRequest() {
    }

    public UpdateSedimentTrapHeaderRequest(Lander landerID, List<ProcessedSedimentTrapData> data) {
        LanderID = landerID;
        this.data = data;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public List<ProcessedSedimentTrapData> getData() {
        return data;
    }

    public void setData(List<ProcessedSedimentTrapData> data) {
        this.data = data;
    }
}
