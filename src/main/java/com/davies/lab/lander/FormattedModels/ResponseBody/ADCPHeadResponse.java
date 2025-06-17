package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.ProcessedADCPData;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ADCPHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Integer dataPointCount;
    private Integer AlignedDataPointCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Set<ADCPDataResponse> data = new HashSet<>();

    public ADCPHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
    }

    public void createDataResponse(ProcessedADCPData newData) {
        ADCPDataResponse res = new ADCPDataResponse(newData);
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

    public Integer getAlignedDataPointCount() {
        return AlignedDataPointCount;
    }

    public void setAlignedDataPointCount(Integer alignedDataPointCount) {
        AlignedDataPointCount = alignedDataPointCount;
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

    public Set<ADCPDataResponse> getData() {
        return data;
    }

    public void setData(Set<ADCPDataResponse> data) {
        this.data = data;
    }

    private class ADCPDataResponse {
        private Long ID;
        private LocalDateTime Date;

        public ADCPDataResponse(ProcessedADCPData data) {
            this.ID = data.getID();
            this.Date = data.getDate();
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
