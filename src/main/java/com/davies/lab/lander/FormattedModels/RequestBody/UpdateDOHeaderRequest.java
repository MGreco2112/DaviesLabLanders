package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.Lander;
import com.davies.lab.lander.Models.ProcessedDOData;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.Set;

public class UpdateDOHeaderRequest {
    private String SondeName;
    private String SondeNo;
    private String SensorType;
    private Integer Channel;
    private Integer DelayTime;
    private Integer PreHeat;
    private Integer MeasModel;
    private Integer BurstTime;
    private Integer BurstCnt;
    private Integer IntervalData;
    private Integer SampleCnt;
    private LocalDateTime StartTime, EndTime;
    private Double DepAdiRho;
    private Date CoefDate;
    private Double Ch1;
    private Double Ch2;
    private Double Ch3;
    private Integer BuzzerEN;
    private Integer BuzzerInterval;
    private String COMMENT;
    private String SensorType2;
    private Integer BuzzerNumber;
    private Integer DepM;
    private Integer SetSal;
    private String FilmNo;
    private Lander LanderID;
    private Set<ProcessedDOData> data;

    public UpdateDOHeaderRequest() {
    }

    public UpdateDOHeaderRequest(String sondeName, String sondeNo, String sensorType, Integer channel, Integer delayTime, Integer preHeat, Integer measModel, Integer burstTime, Integer burstCnt, Integer intervalData, Integer sampleCnt, LocalDateTime startTime, LocalDateTime endTime, Double depAdiRho, Date coefDate, Double ch1, Double ch2, Double ch3, Integer buzzerEN, Integer buzzerInterval, String COMMENT, String sensorType2, Integer buzzerNumber, Integer depM, Integer setSal, String filmNo, Lander landerID, Set<ProcessedDOData> data) {
        SondeName = sondeName;
        SondeNo = sondeNo;
        SensorType = sensorType;
        Channel = channel;
        DelayTime = delayTime;
        PreHeat = preHeat;
        MeasModel = measModel;
        BurstTime = burstTime;
        BurstCnt = burstCnt;
        IntervalData = intervalData;
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
        this.data = data;
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

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public Set<ProcessedDOData> getData() {
        return data;
    }

    public void setData(Set<ProcessedDOData> data) {
        this.data = data;
    }
}
