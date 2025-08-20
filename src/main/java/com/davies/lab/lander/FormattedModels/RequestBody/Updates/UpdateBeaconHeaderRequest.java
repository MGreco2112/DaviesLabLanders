package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import com.davies.lab.lander.Models.Lander;

import java.util.List;

public class UpdateBeaconHeaderRequest {
    private Lander LanderID;
    private List<ProcessedBeaconData> data;

    public UpdateBeaconHeaderRequest() {
    }

    public UpdateBeaconHeaderRequest(Lander landerID, List<ProcessedBeaconData> data) {
        LanderID = landerID;
        this.data = data;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public List<ProcessedBeaconData> getData() {
        return data;
    }

    public void setData(List<ProcessedBeaconData> data) {
        this.data = data;
    }
}
