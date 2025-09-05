package com.davies.lab.lander.FormattedModels.RequestBody.Updates.Head;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Lander;

import java.time.LocalDateTime;
import java.util.List;

public class UpdateCameraHeaderRequest {
    private Lander LanderID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<ProcessedCameraData> data;

    public UpdateCameraHeaderRequest() {
    }

    public UpdateCameraHeaderRequest(Lander landerID, List<ProcessedCameraData> data, LocalDateTime startTime, LocalDateTime endTime) {
        LanderID = landerID;
        this.data = data;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Lander getLanderID() {
        return LanderID;
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
