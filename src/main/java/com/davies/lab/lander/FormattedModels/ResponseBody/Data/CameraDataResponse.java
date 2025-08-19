package com.davies.lab.lander.FormattedModels.ResponseBody.Data;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;

public class CameraDataResponse {
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
