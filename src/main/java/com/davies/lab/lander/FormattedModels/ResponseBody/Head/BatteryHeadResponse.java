package com.davies.lab.lander.FormattedModels.ResponseBody.Head;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;

import java.util.ArrayList;
import java.util.List;

public class BatteryHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Integer dataPointCount;
    private List<BatteryDataResponse> data = new ArrayList<>();

    public BatteryHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
    }

    public BatteryHeadResponse(ProcessedBatteryHeader head) {
        this.HeadID = head.getHeadID();
        this.LanderID = head.getLanderID().getASDBLanderID();
    }

    public void createDataResponse(ProcessedBatteryData dataPoint) {
        BatteryDataResponse res = new BatteryDataResponse(dataPoint);
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

    public List<BatteryDataResponse> getData() {
        return data;
    }

    public void setData(List<BatteryDataResponse> data) {
        this.data = data;
    }

    private class BatteryDataResponse {
        private Long ID;
        private Long HeadID;

        public BatteryDataResponse(Long ID, Long headID) {
            this.ID = ID;
            HeadID = headID;
        }

        public BatteryDataResponse(ProcessedBatteryData data) {
            ID = data.getId();
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
