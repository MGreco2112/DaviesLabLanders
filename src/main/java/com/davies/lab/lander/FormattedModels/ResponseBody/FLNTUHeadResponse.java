package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.ProcessedFLNTUData;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class FLNTUHeadResponse {
    private Long HeadID;
    private String SondeName;
    private String SondeNo;
    private String SensorType;
    private Integer Channel;
    private Integer DelayTime;
    private Integer PreHeat;
    private Integer MeasMode;
    private Integer BurstTime;
    private Integer BurstCnt;
    private Integer IntervalData;
    private Integer WiperInterval;
    private Integer SampleCnt;
    private LocalDateTime StartTime, EndTime;
    private Integer CHLA, CHLB;
    private Date CoefDate;
    private Double Ch1;
    private Double Ch2;
    private Double Ch3;
    private Double Ch4;
    private Integer BuzzerEN;
    private Integer BuzzerInterval;
    private String Comment;
    private String SensorType2;
    private Integer BuzzerNumber;
    private String LanderID;
    private Integer dataPointCount;
    private Set<FLNTUDataResponse> data = new HashSet<>();

    public FLNTUHeadResponse(String sondeName, String sondeNo, String sensorType, Integer channel, Integer delayTime, Integer preHeat, Integer measMode, Integer burstTime, Integer burstCnt, Integer intervalData, Integer wiperInterval, Integer sampleCnt, LocalDateTime startTime, LocalDateTime endTime, Integer CHLA, Integer CHLB, Date coefDate, Double ch1, Double ch2, Double ch3, Double ch4, Integer buzzerEN, Integer buzzerInterval, String comment, String sensorType2, Integer buzzerNumber, String landerID) {
        SondeName = sondeName;
        SondeNo = sondeNo;
        SensorType = sensorType;
        Channel = channel;
        DelayTime = delayTime;
        PreHeat = preHeat;
        MeasMode = measMode;
        BurstTime = burstTime;
        BurstCnt = burstCnt;
        IntervalData = intervalData;
        WiperInterval = wiperInterval;
        SampleCnt = sampleCnt;
        StartTime = startTime;
        EndTime = endTime;
        this.CHLA = CHLA;
        this.CHLB = CHLB;
        CoefDate = coefDate;
        Ch1 = ch1;
        Ch2 = ch2;
        Ch3 = ch3;
        Ch4 = ch4;
        BuzzerEN = buzzerEN;
        BuzzerInterval = buzzerInterval;
        Comment = comment;
        SensorType2 = sensorType2;
        BuzzerNumber = buzzerNumber;
        LanderID = landerID;
    }

    public FLNTUHeadResponse(Long headID, String sondeName, String sondeNo, String sensorType, Integer channel, Integer delayTime, Integer preHeat, Integer measMode, Integer burstTime, Integer burstCnt, Integer intervalData, Integer wiperInterval, Integer sampleCnt, LocalDateTime startTime, LocalDateTime endTime, Integer CHLA, Integer CHLB, Date coefDate, Double ch1, Double ch2, Double ch3, Double ch4, Integer buzzerEN, Integer buzzerInterval, String comment, String sensorType2, Integer buzzerNumber, String landerID) {
        HeadID = headID;
        SondeName = sondeName;
        SondeNo = sondeNo;
        SensorType = sensorType;
        Channel = channel;
        DelayTime = delayTime;
        PreHeat = preHeat;
        MeasMode = measMode;
        BurstTime = burstTime;
        BurstCnt = burstCnt;
        IntervalData = intervalData;
        WiperInterval = wiperInterval;
        SampleCnt = sampleCnt;
        StartTime = startTime;
        EndTime = endTime;
        this.CHLA = CHLA;
        this.CHLB = CHLB;
        CoefDate = coefDate;
        Ch1 = ch1;
        Ch2 = ch2;
        Ch3 = ch3;
        Ch4 = ch4;
        BuzzerEN = buzzerEN;
        BuzzerInterval = buzzerInterval;
        Comment = comment;
        SensorType2 = sensorType2;
        BuzzerNumber = buzzerNumber;
        LanderID = landerID;
    }

    public void createFLNTUDataResponse(ProcessedFLNTUData selData) {
        FLNTUDataResponse newData = new FLNTUDataResponse(
                selData.getID(),
                selData.getDate()
        );

        data.add(newData);
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }

    public String getSondeName() {
        return SondeName;
    }

    public void setSondeName(String sondeName) {
        SondeName = sondeName;
    }

    public String getSondeNo() {
        return SondeNo;
    }

    public void setSondeNo(String sondeNo) {
        SondeNo = sondeNo;
    }

    public String getSensorType() {
        return SensorType;
    }

    public void setSensorType(String sensorType) {
        SensorType = sensorType;
    }

    public Integer getChannel() {
        return Channel;
    }

    public void setChannel(Integer channel) {
        Channel = channel;
    }

    public Integer getDelayTime() {
        return DelayTime;
    }

    public void setDelayTime(Integer delayTime) {
        DelayTime = delayTime;
    }

    public Integer getPreHeat() {
        return PreHeat;
    }

    public void setPreHeat(Integer preHeat) {
        PreHeat = preHeat;
    }

    public Integer getMeasMode() {
        return MeasMode;
    }

    public void setMeasMode(Integer measMode) {
        MeasMode = measMode;
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

    public Integer getIntervalData() {
        return IntervalData;
    }

    public void setIntervalData(Integer intervalData) {
        IntervalData = intervalData;
    }

    public Integer getWiperInterval() {
        return WiperInterval;
    }

    public void setWiperInterval(Integer wiperInterval) {
        WiperInterval = wiperInterval;
    }

    public Integer getSampleCnt() {
        return SampleCnt;
    }

    public void setSampleCnt(Integer sampleCnt) {
        SampleCnt = sampleCnt;
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

    public Integer getCHLA() {
        return CHLA;
    }

    public void setCHLA(Integer CHLA) {
        this.CHLA = CHLA;
    }

    public Integer getCHLB() {
        return CHLB;
    }

    public void setCHLB(Integer CHLB) {
        this.CHLB = CHLB;
    }

    public Date getCoefDate() {
        return CoefDate;
    }

    public void setCoefDate(Date coefDate) {
        CoefDate = coefDate;
    }

    public Double getCh1() {
        return Ch1;
    }

    public void setCh1(Double ch1) {
        Ch1 = ch1;
    }

    public Double getCh2() {
        return Ch2;
    }

    public void setCh2(Double ch2) {
        Ch2 = ch2;
    }

    public Double getCh3() {
        return Ch3;
    }

    public void setCh3(Double ch3) {
        Ch3 = ch3;
    }

    public Double getCh4() {
        return Ch4;
    }

    public void setCh4(Double ch4) {
        Ch4 = ch4;
    }

    public Integer getBuzzerEN() {
        return BuzzerEN;
    }

    public void setBuzzerEN(Integer buzzerEN) {
        BuzzerEN = buzzerEN;
    }

    public Integer getBuzzerInterval() {
        return BuzzerInterval;
    }

    public void setBuzzerInterval(Integer buzzerInterval) {
        BuzzerInterval = buzzerInterval;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public String getSensorType2() {
        return SensorType2;
    }

    public void setSensorType2(String sensorType2) {
        SensorType2 = sensorType2;
    }

    public Integer getBuzzerNumber() {
        return BuzzerNumber;
    }

    public void setBuzzerNumber(Integer buzzerNumber) {
        BuzzerNumber = buzzerNumber;
    }

    public String getLanderID() {
        return LanderID;
    }

    public void setLanderID(String landerID) {
        LanderID = landerID;
    }

    public Integer getDataPointCount() {
        return dataPointCount;
    }

    public void setDataPointCount(Integer dataPointCount) {
        this.dataPointCount = dataPointCount;
    }

    public Set<FLNTUDataResponse> getData() {
        return data;
    }

    public void setData(Set<FLNTUDataResponse> data) {
        this.data = data;
    }

    private class FLNTUDataResponse {
        private Long ID;
        private LocalDateTime date;

        public FLNTUDataResponse(Long ID, LocalDateTime date) {
            this.ID = ID;
            this.date = date;
        }

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public LocalDateTime getDate() {
            return date;
        }

        public void setDate(LocalDateTime date) {
            this.date = date;
        }
    }
}
