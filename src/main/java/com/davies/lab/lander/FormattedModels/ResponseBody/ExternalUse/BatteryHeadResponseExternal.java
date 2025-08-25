package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;

import java.util.List;

public class BatteryHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private List<BatteryDataResponseExternal> data;

    public BatteryHeadResponseExternal() {
    }

    public BatteryHeadResponseExternal(ProcessedBatteryHeader head) {
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

    public List<BatteryDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<BatteryDataResponseExternal> data) {
        this.data = data;
    }
}
