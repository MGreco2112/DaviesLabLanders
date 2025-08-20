package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Lander;

import java.util.List;

public class UpdateCameraHeaderRequest {
    private Lander LanderID;
    private List<ProcessedCameraData> data;

    public UpdateCameraHeaderRequest() {
    }

    public UpdateCameraHeaderRequest(Lander landerID, List<ProcessedCameraData> data) {
        LanderID = landerID;
        this.data = data;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public List<ProcessedCameraData> getData() {
        return data;
    }

    public void setData(List<ProcessedCameraData> data) {
        this.data = data;
    }
}
