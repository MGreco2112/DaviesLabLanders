package com.davies.lab.lander.FormattedModels.ResponseBody.Head;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;

import java.util.List;

public class CameraHeadResponse {
    private Long HeadID;

    private String LanderID;
    private Integer dataPointCount;
    private List<CameraDataResponse> data;

    public CameraHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
    }

    public CameraHeadResponse(ProcessedCameraHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
    }

    public void createDataResponse(ProcessedCameraData dataPoint) {
        CameraDataResponse temp = new CameraDataResponse(dataPoint);

        data.add(temp);
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
