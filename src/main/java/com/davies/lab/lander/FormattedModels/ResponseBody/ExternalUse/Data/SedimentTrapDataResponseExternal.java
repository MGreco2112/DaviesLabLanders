package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse.Data;

import com.davies.lab.lander.Models.Data.ProcessedSedimentTrapData;

import java.util.ArrayList;
import java.util.List;

public class SedimentTrapDataResponseExternal {
    private Long ID;
    private Long HeadID;

    public SedimentTrapDataResponseExternal() {
    }

    public SedimentTrapDataResponseExternal(ProcessedSedimentTrapData data) {
        ID = data.getID();
        HeadID = data.getHeadID().getHeadID();
    }

    public static List<SedimentTrapDataResponseExternal> createDataResponse(List<ProcessedSedimentTrapData> data) {
        List<SedimentTrapDataResponseExternal> responseList = new ArrayList<>();

        for (ProcessedSedimentTrapData dataPoint : data) {
            responseList.add(new SedimentTrapDataResponseExternal(dataPoint));
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
