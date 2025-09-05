package com.davies.lab.lander.FormattedModels.ResponseBody.Head;

import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SedimentTrapHeadResponse {
    private Long HeadID;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String LanderID;
    private Integer dataPointCount;
    private List<SedimentTrapDataResponse> data = new ArrayList<>();

    public SedimentTrapHeadResponse(Long headID, String landerID, LocalDateTime startTime, LocalDateTime endTime) {
        HeadID = headID;
        LanderID = landerID;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public SedimentTrapHeadResponse(ProcessedSedimentTrapHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
    }

    public void createDataResponse(ProcessedSedimentTrapData dataPoint) {
        SedimentTrapDataResponse temp = new SedimentTrapDataResponse(
                dataPoint
        );

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

    public List<SedimentTrapDataResponse> getData() {
        return data;
    }

    public void setData(List<SedimentTrapDataResponse> data) {
        this.data = data;
    }

    private class SedimentTrapDataResponse {
        private Long ID;
        private Long HeadID;

        public SedimentTrapDataResponse(Long ID, Long headID) {
            this.ID = ID;
            HeadID = headID;
        }

        public SedimentTrapDataResponse(ProcessedSedimentTrapData data) {
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
