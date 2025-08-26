package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;

import java.util.ArrayList;
import java.util.List;

public class CameraDataResponseExternal {
    private Long ID;
    private Long HeadID;

    public CameraDataResponseExternal() {
    }

    public CameraDataResponseExternal(ProcessedCameraData data) {
        ID = data.getID();
        HeadID = data.getHeadID().getHeadID();
    }

    public static List<CameraDataResponseExternal> createDataResponse(List<ProcessedCameraData> data) {
        List<CameraDataResponseExternal> responseList = new ArrayList<>();

        for (ProcessedCameraData dataPoint : data) {
            responseList.add(new CameraDataResponseExternal(dataPoint));
        }

        return responseList;
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
