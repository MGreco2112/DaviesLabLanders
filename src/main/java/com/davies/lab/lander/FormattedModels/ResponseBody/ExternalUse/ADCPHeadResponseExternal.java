package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Headers.ProcessedADCPHead;

import java.util.ArrayList;
import java.util.List;

public class ADCPHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private List<ADCPDataResponseExternal> data = new ArrayList<>();

    public ADCPHeadResponseExternal() {
    }

    public ADCPHeadResponseExternal(ProcessedADCPHead head) {
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

    public List<ADCPDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<ADCPDataResponseExternal> data) {
        this.data = data;
    }
}
