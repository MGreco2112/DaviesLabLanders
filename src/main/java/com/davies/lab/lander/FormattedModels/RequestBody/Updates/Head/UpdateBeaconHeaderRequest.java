package com.davies.lab.lander.FormattedModels.RequestBody.Updates.Head;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import com.davies.lab.lander.Models.Lander;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateBeaconHeaderRequest {
    private Lander LanderID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<ProcessedBeaconData> data;

    public UpdateBeaconHeaderRequest() {
    }

    public UpdateBeaconHeaderRequest(Lander landerID, List<ProcessedBeaconData> data, LocalDateTime startTime, LocalDateTime endTime) {
        LanderID = landerID;
        this.data = data;
        this.startTime = startTime;
        this.endTime = endTime;
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

    public List<ProcessedBeaconData> getData() {
        return data;
    }

    public void setData(List<ProcessedBeaconData> data) {
        this.data = data;
    }
}
