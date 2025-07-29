package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.ProcessedAlbexCTDData;
import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AlbexCTDHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Integer dataPointCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<AlbexCTDDataResponse> data = new ArrayList<>();

    public AlbexCTDHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
    }

    public AlbexCTDHeadResponse(ProcessedAlbexCTDHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
        dataPointCount = head.getData().size();
        startTime = head.getLanderID().getDeploymentDateAndTime();
        endTime = head.getLanderID().getRecoveryDateAndTime();
    }

    public void createDataResponse(ProcessedAlbexCTDData AlbexData) {
        AlbexCTDDataResponse res = new AlbexCTDDataResponse(AlbexData.getID(), AlbexData.getDate());
        data.add(res);
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
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

    public List<AlbexCTDDataResponse> getData() {
        return data;
    }

    public void setData(List<AlbexCTDDataResponse> data) {
        this.data = data;
    }

    private class AlbexCTDDataResponse {
        private Long ID;
        private LocalDateTime Date;

        public AlbexCTDDataResponse(Long ID, LocalDateTime date) {
            this.ID = ID;
            Date = date;
        }

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public LocalDateTime getDate() {
            return Date;
        }

        public void setDate(LocalDateTime date) {
            Date = date;
        }
    }
}
