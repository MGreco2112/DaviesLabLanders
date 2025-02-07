package com.davies.lab.lander.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProcessedDOHead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer HeadID;
    private String SondeName;
    private String SondeNo;
    private String SensorType;
    private Integer Channel;
    private Integer DelayTime;
    private Integer PreHeat;
    private Integer MeasModel;
    private Integer BurstTime;
    private Integer BurstCnt;
    private Integer Interval;
    private Integer SampleCnt;
    private String StartTime, EndTime;
    private Double DepAdiRho;
    private Double CoefDate;
    private Double Ch1, Ch2, Ch3;
    private Integer BuzzerEN;
    private Integer BuzzerInterval;
    private String COMMENT;
    private String SensorType2;
    private Integer BuzzerNumber;
    private Integer DepM;
    private Integer SetSal;
    private String FilmNo;
    private String LanderID;

    public ProcessedDOHead() {
    }

    public ProcessedDOHead(Integer headID, String sondeName, String sondeNo, String sensorType, Integer channel, Integer delayTime, Integer preHeat, Integer measModel, Integer burstTime, Integer burstCnt, Integer interval, Integer sampleCnt, String startTime, String endTime, Double depAdiRho, Double coefDate, Double ch1, Double ch2, Double ch3, Integer buzzerEN, Integer buzzerInterval, String COMMENT, String sensorType2, Integer buzzerNumber, Integer depM, Integer setSal, String filmNo, String landerID) {
        HeadID = headID;
        SondeName = sondeName;
        SondeNo = sondeNo;
        SensorType = sensorType;
        Channel = channel;
        DelayTime = delayTime;
        PreHeat = preHeat;
        MeasModel = measModel;
        BurstTime = burstTime;
        BurstCnt = burstCnt;
        Interval = interval;
        SampleCnt = sampleCnt;
        StartTime = startTime;
        EndTime = endTime;
        DepAdiRho = depAdiRho;
        CoefDate = coefDate;
        Ch1 = ch1;
        Ch2 = ch2;
        Ch3 = ch3;
        BuzzerEN = buzzerEN;
        BuzzerInterval = buzzerInterval;
        this.COMMENT = COMMENT;
        SensorType2 = sensorType2;
        BuzzerNumber = buzzerNumber;
        DepM = depM;
        SetSal = setSal;
        FilmNo = filmNo;
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

    public Integer getMeasModel() {
        return MeasModel;
    }

    public void setMeasModel(Integer measModel) {
        MeasModel = measModel;
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

    public Double getCoefDate() {
        return CoefDate;
    }

    public void setCoefDate(Double coefDate) {
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

    public Integer getSetSal() {
        return SetSal;
    }

    public void setSetSal(Integer setSal) {
        SetSal = setSal;
    }

    public String getFilmNo() {
        return FilmNo;
    }

    public void setFilmNo(String filmNo) {
        FilmNo = filmNo;
    }

    public String getLanderID() {
        return LanderID;
    }

    public void setLanderID(String landerID) {
        LanderID = landerID;
    }
}
