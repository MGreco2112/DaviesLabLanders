package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Head;

import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Data.BeaconDataResponseExternal;
import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;

import java.util.List;

public class BeaconHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private List<BeaconDataResponseExternal> data;

    public BeaconHeadResponseExternal() {

    }

    public BeaconHeadResponseExternal(ProcessedBeaconHeader head) {
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

    public List<BeaconDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<BeaconDataResponseExternal> data) {
        this.data = data;
    }
}
