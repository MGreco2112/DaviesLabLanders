package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.ProcessedCTDHead;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CTDHeadResponseExternal {
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
    private Integer SampleCnt;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;
    private Double DepAdiRho;
    private Integer ECA;
    private Integer ECB;
    private Integer ECDeg;
    private Double ECCoef;
    private Date CoefDate;
    private Double Ch1;
    private Double Ch2;
    private Double Ch3;
    private Double Ch4;
    private Integer BuzzerEN;
    private Integer BuzzerInterval;
    private String COMMENT;
    private String SensorType2;
    private Integer BuzzerNumber;
    private Integer DepM;
    private Integer CondDepB;
    private String LanderID;
    private List<CTDDataResponseExternal> data = new ArrayList<>();

    public CTDHeadResponseExternal() {
    }

    public CTDHeadResponseExternal (ProcessedCTDHead head) {
        HeadID = head.getHeadID();
        SondeName = head.getSondeName();
        SondeNo = head.getSondeNo();
        SensorType = head.getSensorType();
        Channel = head.getChannel();
        DelayTime = head.getDelayTime();
        PreHeat = head.getPreHeat();
        MeasMode = head.getMeasMode();
        BurstTime = head.getBurstTime();
        BurstCnt = head.getBurstCnt();
        IntervalData = head.getIntervalData();
        SampleCnt = head.getSampleCnt();
        StartTime = head.getStartTime();
        EndTime = head.getEndTime();
        DepAdiRho = head.getDepAdiRho();
        this.ECA = head.getECA();
        this.ECB = head.getECB();
        this.ECDeg = head.getECDeg();
        this.ECCoef = head.getECCoef();
        CoefDate = head.getCoefDate();
        Ch1 = head.getCh1();
        Ch2 = head.getCh2();
        Ch3 = head.getCh3();
        Ch4 = head.getCh4();
        BuzzerEN = head.getBuzzerEN();
        BuzzerInterval = head.getBuzzerInterval();
        this.COMMENT = head.getCOMMENT();
        SensorType2 = head.getSensorType2();
        BuzzerNumber = head.getBuzzerNumber();
        DepM = head.getDepM();
        CondDepB = head.getCondDepB();
        LanderID = head.getLanderID().getASDBLanderID();
    }

    public void addCTDData(CTDDataResponseExternal newData) {
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

    public Double getDepAdiRho() {
        return DepAdiRho;
    }

    public void setDepAdiRho(Double depAdiRho) {
        DepAdiRho = depAdiRho;
    }

    public Integer getECA() {
        return ECA;
    }

    public void setECA(Integer ECA) {
        this.ECA = ECA;
    }

    public Integer getECB() {
        return ECB;
    }

    public void setECB(Integer ECB) {
        this.ECB = ECB;
    }

    public Integer getECDeg() {
        return ECDeg;
    }

    public void setECDeg(Integer ECDeg) {
        this.ECDeg = ECDeg;
    }

    public Double getECCoef() {
        return ECCoef;
    }

    public void setECCoef(Double ECCoef) {
        this.ECCoef = ECCoef;
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

    public String getCOMMENT() {
        return COMMENT;
    }

    public void setCOMMENT(String COMMENT) {
        this.COMMENT = COMMENT;
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

    public Integer getDepM() {
        return DepM;
    }

    public void setDepM(Integer depM) {
        DepM = depM;
    }

    public Integer getCondDepB() {
        return CondDepB;
    }

    public void setCondDepB(Integer condDepB) {
        CondDepB = condDepB;
    }

    public String getLanderID() {
        return LanderID;
    }

    public void setLanderID(String landerID) {
        LanderID = landerID;
    }

    public List<CTDDataResponseExternal> getData() {
        return data;
    }

    public void setData(List<CTDDataResponseExternal> data) {
        this.data = data;
    }
}
