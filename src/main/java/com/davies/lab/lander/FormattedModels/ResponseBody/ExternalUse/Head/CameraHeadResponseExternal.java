package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Head;

import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Data.CameraDataResponseExternal;
import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;

import java.util.List;

public class CameraHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private List<CameraDataResponseExternal> data;

    public CameraHeadResponseExternal() {
    }

    public CameraHeadResponseExternal(ProcessedCameraHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
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

    public List<CameraDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<CameraDataResponseExternal> data) {
        this.data = data;
    }
}
