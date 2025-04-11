package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.*;

import java.util.HashSet;
import java.util.Set;

public class LanderResponse {
    private String ASDBLanderID;
    private String LanderPlatform;
    private String ADDBROVDiveID;
    private CTDHeadResponse CTDHead;
    private DOHeadResponse DOHead;
    private FLNTUHeadResponse FLNTUHead;

    public LanderResponse(String ASDBLanderID, String landerPlatform, String ADDBROVDiveID) {
        this.ASDBLanderID = ASDBLanderID;
        LanderPlatform = landerPlatform;
        this.ADDBROVDiveID = ADDBROVDiveID;
    }

    public void createCTDHeadResponse(ProcessedCTDHead head) {
        CTDHead = new CTDHeadResponse(head.getHeadID());
    }

    public void createDOHeadResponse(ProcessedDOHead head) {
        DOHead = new DOHeadResponse(head.getHeadID());
    }

    public void createFLNTUHeadResponse(ProcessedFLNTUHead head) {
        FLNTUHead = new FLNTUHeadResponse(head.getHeadID());
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

    public CTDHeadResponse getCTDHead() {
        return CTDHead;
    }

    public void setCTDHead(CTDHeadResponse CTDHead) {
        this.CTDHead = CTDHead;
    }

    public DOHeadResponse getDOHead() {
        return DOHead;
    }

    public void setDOHead(DOHeadResponse DOHead) {
        this.DOHead = DOHead;
    }

    public FLNTUHeadResponse getFLNTUHead() {
        return FLNTUHead;
    }

    public void setFLNTUHead(FLNTUHeadResponse FLNTUHead) {
        this.FLNTUHead = FLNTUHead;
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

