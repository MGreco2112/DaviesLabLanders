package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ALBEXCTDHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private List<ALBEXCTDDataResponseExternal> data = new ArrayList<>();

    public ALBEXCTDHeadResponseExternal() {
    }

    public ALBEXCTDHeadResponseExternal(ProcessedAlbexCTDHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
    }

    public void addCTDData(ALBEXCTDDataResponseExternal newData) {
        data.add(newData);
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

    public List<ALBEXCTDDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<ALBEXCTDDataResponseExternal> data) {
        this.data = data;
    }
}
