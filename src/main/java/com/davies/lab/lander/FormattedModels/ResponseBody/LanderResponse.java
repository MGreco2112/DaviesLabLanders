package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.*;

import java.time.LocalDateTime;

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
        CTDHead = new CTDHeadResponse(head.getHeadID(), head.getBurstTime(), head.getBurstCnt(), head.getStartTime(), head.getEndTime());
    }

    public void createDOHeadResponse(ProcessedDOHead head) {
        DOHead = new DOHeadResponse(head.getHeadID(), head.getBurstTime(), head.getBurstCnt(), head.getStartTime(), head.getEndTime());
    }

    public void createFLNTUHeadResponse(ProcessedFLNTUHead head) {
        FLNTUHead = new FLNTUHeadResponse(head.getHeadID(), head.getBurstTime(), head.getBurstCnt(), head.getStartTime(), head.getEndTime());
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
        private Long HeadID;
        private Integer burstTime;
        private Integer burstCnt;
        private LocalDateTime StartTime;
        private LocalDateTime EndTime;

        public CTDHeadResponse(Long id) {
            HeadID = id;
        }

        public CTDHeadResponse(Long headID, Integer burstTime, Integer burstCnt, LocalDateTime startTime, LocalDateTime endTime) {
            HeadID = headID;
            this.burstTime = burstTime;
            this.burstCnt = burstCnt;
            StartTime = startTime;
            EndTime = endTime;
        }

        public Long getHeadID() {
            return HeadID;
        }

        public void setHeadID(Long headID) {
            HeadID = headID;
        }

        public Integer getBurstTime() {
            return burstTime;
        }

        public void setBurstTime(Integer burstTime) {
            this.burstTime = burstTime;
        }

        public Integer getBurstCnt() {
            return burstCnt;
        }

        public void setBurstCnt(Integer burstCnt) {
            this.burstCnt = burstCnt;
        }

        public LocalDateTime getStartTime() {
            return StartTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            StartTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return EndTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            EndTime = endTime;
        }
    }

    private class DOHeadResponse {
        private Long HeadID;
        private Integer BurstTime;
        private Integer BurstCnt;
        private LocalDateTime StartTime;
        private LocalDateTime endTime;

        public DOHeadResponse(Long id) {
            HeadID = id;
        }

        public DOHeadResponse(Long headID, Integer burstTime, Integer burstCnt, LocalDateTime startTime, LocalDateTime endTime) {
            HeadID = headID;
            BurstTime = burstTime;
            BurstCnt = burstCnt;
            StartTime = startTime;
            this.endTime = endTime;
        }

        public Long getHeadID() {
            return HeadID;
        }

        public void setHeadID(Long headID) {
            HeadID = headID;
        }

        public Integer getBurstTime() {
            return BurstTime;
        }

        public void setBurstTime(Integer burstTime) {
            BurstTime = burstTime;
        }

        public Integer getBurstCnt() {
            return BurstCnt;
        }

        public void setBurstCnt(Integer burstCnt) {
            BurstCnt = burstCnt;
        }

        public LocalDateTime getStartTime() {
            return StartTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            StartTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return endTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            this.endTime = endTime;
        }
    }

    private class FLNTUHeadResponse {
        private Long HeadID;
        private Integer BurstTime;
        private Integer BurstCnt;
        private LocalDateTime StartTime;
        private LocalDateTime EndTime;

        public FLNTUHeadResponse(Long id) {
            HeadID = id;
        }

        public FLNTUHeadResponse(Long headID, Integer burstTime, Integer burstCnt, LocalDateTime startTime, LocalDateTime endTime) {
            HeadID = headID;
            BurstTime = burstTime;
            BurstCnt = burstCnt;
            StartTime = startTime;
            EndTime = endTime;
        }

        public Long getHeadID() {
            return HeadID;
        }

        public void setHeadID(Long headID) {
            HeadID = headID;
        }

        public Integer getBurstTime() {
            return BurstTime;
        }

        public void setBurstTime(Integer burstTime) {
            BurstTime = burstTime;
        }

        public Integer getBurstCnt() {
            return BurstCnt;
        }

        public void setBurstCnt(Integer burstCnt) {
            BurstCnt = burstCnt;
        }

        public LocalDateTime getStartTime() {
            return StartTime;
        }

        public void setStartTime(LocalDateTime startTime) {
            StartTime = startTime;
        }

        public LocalDateTime getEndTime() {
            return EndTime;
        }

        public void setEndTime(LocalDateTime endTime) {
            EndTime = endTime;
        }
    }

}

