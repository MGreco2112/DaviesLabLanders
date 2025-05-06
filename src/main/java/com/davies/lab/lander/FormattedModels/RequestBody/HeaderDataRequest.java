package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.HelperClasses.StringFormatting;

import java.time.LocalDateTime;

public class HeaderDataRequest {
    private Integer burstCnt;
    private Integer burstTime;
    private String startTime;
    private String endTime;

    public HeaderDataRequest() {
    }

    public HeaderDataRequest(Integer burstCnt, Integer burstTime, String startTime, String endTime) {
        this.burstCnt = burstCnt;
        this.burstTime = burstTime;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Integer getBurstCnt() {
        return burstCnt;
    }

    public void setBurstCnt(Integer burstCnt) {
        this.burstCnt = burstCnt;
    }

    public Integer getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(Integer burstTime) {
        this.burstTime = burstTime;
    }

    public LocalDateTime getStartTime() {
        return StringFormatting.formatFrontendDateString(startTime);
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return StringFormatting.formatFrontendDateString(endTime);
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
