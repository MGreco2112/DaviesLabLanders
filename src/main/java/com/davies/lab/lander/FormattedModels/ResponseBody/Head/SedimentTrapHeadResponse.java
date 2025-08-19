package com.davies.lab.lander.FormattedModels.ResponseBody.Head;

import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;
import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;

import java.util.ArrayList;
import java.util.List;

public class SedimentTrapHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Integer dataPointCount;
    private List<SedimentTrapDataResponse> data = new ArrayList<>();

    public SedimentTrapHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
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
