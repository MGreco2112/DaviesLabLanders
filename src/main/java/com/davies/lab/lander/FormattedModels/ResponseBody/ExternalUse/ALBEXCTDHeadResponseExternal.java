package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedAlbexCTDHeader;

import java.util.HashSet;
import java.util.Set;

public class ALBEXCTDHeadResponseExternal {
    private Long HeadID;
    private String LanderID;
    private Set<ALBEXCTDDataResponseExternal> data = new HashSet<>();

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

    public Set<ALBEXCTDDataResponseExternal> getData() {
        return data;
    }

    public void setData(Set<ALBEXCTDDataResponseExternal> data) {
        this.data = data;
    }
}
