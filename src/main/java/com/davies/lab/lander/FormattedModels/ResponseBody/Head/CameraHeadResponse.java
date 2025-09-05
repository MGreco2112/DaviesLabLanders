package com.davies.lab.lander.FormattedModels.ResponseBody.Head;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;

import java.time.LocalDateTime;
import java.util.List;

public class CameraHeadResponse {
    private Long HeadID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String LanderID;
    private Integer dataPointCount;
    private List<CameraDataResponse> data;

    public CameraHeadResponse(Long headID, String landerID, LocalDateTime startTime, LocalDateTime endTime) {
        HeadID = headID;
        LanderID = landerID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public CameraHeadResponse(ProcessedCameraHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
        startTime = head.getStartTime();
        endTime = head.getEndTime();
    }

    public void createDataResponse(ProcessedCameraData dataPoint) {
        CameraDataResponse temp = new CameraDataResponse(dataPoint);

        data.add(temp);
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
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

    public String getLanderID() {
        return LanderID;
    }

    public void setLanderID(String landerID) {
        LanderID = landerID;
    }

    public Integer getDataPointCount() {
        return dataPointCount;
    }

    public void setDataPointCount(Integer dataPointCount) {
        this.dataPointCount = dataPointCount;
    }

    public List<CameraDataResponse> getData() {
        return data;
    }

    public void setData(List<CameraDataResponse> data) {
        this.data = data;
    }

    private class CameraDataResponse {
        private Long ID;
        private Long HeadID;

        public CameraDataResponse(Long ID, Long headID) {
            this.ID = ID;
            HeadID = headID;
        }

        public CameraDataResponse(ProcessedCameraData data) {
            ID = data.getID();
            HeadID = data.getHeadID().getHeadID();
        }

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public Long getHeadID() {
            return HeadID;
        }

        public void setHeadID(Long headID) {
            HeadID = headID;
        }
    }
}
