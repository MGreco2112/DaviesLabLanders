package com.davies.lab.lander.Models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class ProcessedCTDHead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer HeadID;
    private String SondeName;
    private String SondeNo;
    private String SensorType;
    private Integer Channel;
    private Integer DelayTime;
    private Integer PreHeat;
    private Integer MeasMode;
    private Integer BurstTime;
    private Integer BurstCnt;
    private Integer Interval;
    private Integer SampleCnt;
    private String StartTime;
    private String EndTime;
    private Double DepAdiRho;
    private Integer ECA;
    private Integer ECB;
    private Integer ECDeg;
    private Double ECCoef;
    private String CoefDate;
    private Double Ch1, Ch2, Ch3, Ch4;
    private Integer BuzzerEN;
    private Integer BuzzerInterval;
    private String COMMENT;
    private String SensorType2;
    private Integer BuzzerNumber;
    private Integer DepM;
    private Integer CondDepB;
    private String LanderID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("HeadID")
    private Set<ProcessedCTDData> data;

    public ProcessedCTDHead() {
    }

    public ProcessedCTDHead(Integer headID, String sondeName, String sondeNo, String sensorType, Integer channel, Integer delayTime, Integer preHeat, Integer measMode, Integer burstTime, Integer burstCnt, Integer interval, Integer sampleCnt, String startTime, String endTime, Double depAdiRho, Integer ECA, Integer ECB, Integer ECDeg, Double ECCoef, String coefDate, Double ch1, Double ch2, Double ch3, Double ch4, Integer buzzerEN, Integer buzzerInterval, String COMMENT, String sensorType2, Integer buzzerNumber, Integer depM, Integer condDepB, String landerID) {
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
        Interval = interval;
        SampleCnt = sampleCnt;
        StartTime = startTime;
        EndTime = endTime;
        DepAdiRho = depAdiRho;
        this.ECA = ECA;
        this.ECB = ECB;
        this.ECDeg = ECDeg;
        this.ECCoef = ECCoef;
        CoefDate = coefDate;
        Ch1 = ch1;
        Ch2 = ch2;
        Ch3 = ch3;
        Ch4 = ch4;
        BuzzerEN = buzzerEN;
        BuzzerInterval = buzzerInterval;
        this.COMMENT = COMMENT;
        SensorType2 = sensorType2;
        BuzzerNumber = buzzerNumber;
        DepM = depM;
        CondDepB = condDepB;
        LanderID = landerID;
    }

    public Integer getHeadID() {
        return HeadID;
    }

    public void setHeadID(Integer headID) {
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

    public Integer getInterval() {
        return Interval;
    }

    public void setInterval(Integer interval) {
        Interval = interval;
    }

    public Integer getSampleCnt() {
        return SampleCnt;
    }

    public void setSampleCnt(Integer sampleCnt) {
        SampleCnt = sampleCnt;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String startTime) {
        StartTime = startTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
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

    public String getCoefDate() {
        return CoefDate;
    }

    public void setCoefDate(String coefDate) {
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

    public Set<ProcessedCTDData> getData() {
        return data;
    }

    public void setData(Set<ProcessedCTDData> data) {
        this.data = data;
    }
}
