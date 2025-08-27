package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Head;

import com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Data.SedimentTrapDataResponseExternal;
import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;

import java.util.List;

public class SedimentTrapHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private List<SedimentTrapDataResponseExternal> data;

    public SedimentTrapHeadResponseExternal() {
    }

    public SedimentTrapHeadResponseExternal(ProcessedSedimentTrapHeader head) {
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

    public List<SedimentTrapDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<SedimentTrapDataResponseExternal> data) {
        this.data = data;
    }
}
