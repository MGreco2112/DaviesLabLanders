package com.davies.lab.lander.FormattedModels.ResponseBody.Head;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;

import java.util.ArrayList;
import java.util.List;

public class BeaconHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Integer dataPointCount;
    private List<BeaconDataResponse> data = new ArrayList<>();

    public BeaconHeadResponse(Long headID, String landerID, Integer dataPointCount) {
        HeadID = headID;
        LanderID = landerID;
        this.dataPointCount = dataPointCount;
    }

    public BeaconHeadResponse(ProcessedBeaconHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
    }

    public void createDataResponse(ProcessedBeaconData dataPoint) {
        BeaconDataResponse res = new BeaconDataResponse(dataPoint);
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

    public List<BeaconDataResponse> getData() {
        return data;
    }

    public void setData(List<BeaconDataResponse> data) {
        this.data = data;
    }

    private class BeaconDataResponse {
        private Long ID;
        private Long HeadID;

        public BeaconDataResponse(Long ID, Long headID) {
            this.ID = ID;
            HeadID = headID;
        }

        public BeaconDataResponse(ProcessedBeaconData data) {
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
