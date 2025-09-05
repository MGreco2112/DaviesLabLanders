package com.davies.lab.lander.FormattedModels.RequestBody.Updates.Head;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import com.davies.lab.lander.Models.Lander;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateBatteryHeaderRequest {
    private Lander LanderID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<ProcessedBatteryData> data;

    public UpdateBatteryHeaderRequest() {
    }

    public UpdateBatteryHeaderRequest(Lander landerID, LocalDateTime startTime, LocalDateTime endTime, List<ProcessedBatteryData> data) {
        LanderID = landerID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.data = data;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<ProcessedBatteryData> getData() {
        return data;
    }

    public void setData(List<ProcessedBatteryData> data) {
        this.data = data;
    }
}
