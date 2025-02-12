package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.*;

import java.util.HashSet;
import java.util.Set;

public class LanderResponse {
    private String ASDBLanderID;
    private String LanderPlatform;
    private String ADDBROVDiveID;
    private Set<CTDHeadResponse> CTDHeads = new HashSet<>();
    private Set<DOHeadResponse> DOHeads = new HashSet<>();
    private Set<FLNTUHeadResponse> FLNTUHeads = new HashSet<>();

    public LanderResponse(String ASDBLanderID, String landerPlatform, String ADDBROVDiveID) {
        this.ASDBLanderID = ASDBLanderID;
        LanderPlatform = landerPlatform;
        this.ADDBROVDiveID = ADDBROVDiveID;
    }

    public void createCTDHeadResponse(ProcessedCTDHead head) {
        CTDHeadResponse res = new CTDHeadResponse(head.getHeadID());

        CTDHeads.add(res);
    }

    public void createDOHeadResponse(ProcessedDOHead head) {
        DOHeadResponse res = new DOHeadResponse(head.getHeadID());

        DOHeads.add(res);
    }

    public void createFLNTUHeadResponse(ProcessedFLNTUHead head) {
        FLNTUHeadResponse res = new FLNTUHeadResponse(head.getHeadID());

        FLNTUHeads.add(res);
    }

    public String getASDBLanderID() {
        return ASDBLanderID;
    }

    public void setASDBLanderID(String ASDBLanderID) {
        this.ASDBLanderID = ASDBLanderID;
    }

    public String getLanderPlatform() {
        return LanderPlatform;
    }

    public void setLanderPlatform(String landerPlatform) {
        LanderPlatform = landerPlatform;
    }

    public String getADDBROVDiveID() {
        return ADDBROVDiveID;
    }

    public void setADDBROVDiveID(String ADDBROVDiveID) {
        this.ADDBROVDiveID = ADDBROVDiveID;
    }

    public Set<CTDHeadResponse> getCTDHeads() {
        return CTDHeads;
    }

    public void setCTDHeads(Set<CTDHeadResponse> CTDHeads) {
        this.CTDHeads = CTDHeads;
    }

    public Set<DOHeadResponse> getDOHeads() {
        return DOHeads;
    }

    public void setDOHeads(Set<DOHeadResponse> DOHeads) {
        this.DOHeads = DOHeads;
    }

    public Set<FLNTUHeadResponse> getFLNTUHeads() {
        return FLNTUHeads;
    }

    public void setFLNTUHeads(Set<FLNTUHeadResponse> FLNTUHeads) {
        this.FLNTUHeads = FLNTUHeads;
    }

    private class CTDHeadResponse {
        private Integer HeadID;

        public CTDHeadResponse(Integer id) {
            HeadID = id;
        }

        public Integer getHeadID() {
            return HeadID;
        }

        public void setHeadID(Integer headID) {
            HeadID = headID;
        }
    }

    private class DOHeadResponse {
        private Integer HeadID;

        public DOHeadResponse(Integer id) {
            HeadID = id;
        }

        public Integer getHeadID() {
            return HeadID;
        }

        public void setHeadID(Integer headID) {
            HeadID = headID;
        }
    }

    private class FLNTUHeadResponse {
        private Integer HeadID;

        public FLNTUHeadResponse(Integer id) {
            HeadID = id;
        }

        public Integer getHeadID() {
            return HeadID;
        }

        public void setHeadID(Integer headID) {
            HeadID = headID;
        }
    }

}

