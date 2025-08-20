package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import com.davies.lab.lander.Models.Lander;

import java.util.List;

public class UpdateBatteryRequest {
    private Lander LanderID;
    private List<ProcessedBatteryData> data;

    public UpdateBatteryRequest() {
    }

    public UpdateBatteryRequest(Lander landerID, List<ProcessedBatteryData> data) {
        LanderID = landerID;
        this.data = data;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public List<ProcessedBatteryData> getData() {
        return data;
    }

    public void setData(List<ProcessedBatteryData> data) {
        this.data = data;
    }
}
