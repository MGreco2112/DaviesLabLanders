package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.ProcessedDOData;
import com.davies.lab.lander.Models.ProcessedDOHead;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DOHeadResponse {
    private Long HeadID;
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
    private String LanderID;
    private Integer dataPointCount;
    private List<DODataResponse> data = new ArrayList<>();

    public DOHeadResponse(Long headID, String sondeName, String sondeNo, String sensorType, Integer channel, Integer delayTime, Integer preHeat, Integer measModel, Integer burstTime, Integer burstCnt, Integer intervalData, Integer sampleCnt, LocalDateTime startTime, LocalDateTime endTime, Double depAdiRho, Date coefDate, Double ch1, Double ch2, Double ch3, Integer buzzerEN, Integer buzzerInterval, String COMMENT, String sensorType2, Integer buzzerNumber, Integer depM, Integer setSal, String filmNo, String landerID) {
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
    }

    public DOHeadResponse(ProcessedDOHead head) {
        HeadID = head.getHeadID();
        SondeName = head.getSondeName();
        SondeNo = head.getSondeNo();
        SensorType = head.getSensorType();
        Channel = head.getChannel();
        DelayTime = head.getDelayTime();
        PreHeat = head.getPreHeat();
        MeasModel = head.getMeasModel();
        BurstTime = head.getBurstTime();
        BurstCnt = head.getBurstCnt();
        IntervalData = head.getIntervalData();
        SampleCnt = head.getSampleCnt();
        StartTime = head.getStartTime();
        EndTime = head.getEndTime();
        DepAdiRho = head.getDepAdiRho();
        CoefDate = head.getCoefDate();
        Ch1 = head.getCh1();
        Ch2 = head.getCh2();
        Ch3 = head.getCh3();
        BuzzerEN = head.getBuzzerEN();
        BuzzerInterval = head.getBuzzerInterval();
        COMMENT = head.getCOMMENT();
        SensorType2 = head.getSensorType2();
        BuzzerNumber = head.getBuzzerNumber();
        DepM = head.getDepM();
        SetSal = head.getSetSal();
        FilmNo = head.getFilmNo();
        LanderID = head.getLanderID().getASDBLanderID();
    }

    public void createDODataResponse(ProcessedDOData dataPoint) {
        DODataResponse temp = new DODataResponse(
                dataPoint.getID(),
                dataPoint.getDate()
        );

        data.add(temp);
    }

    public void createFullDataResponse(ProcessedDOData dataPoint) {
        DODataResponse temp = new DODataResponse(dataPoint);
        data.add(temp);
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

    public List<DODataResponse> getData() {
        return data;
    }

    public void setData(List<DODataResponse> data) {
        this.data = data;
    }

    private class DODataResponse {
        private Long ID;
        private LocalDateTime Date;
        private Double TempDegC;
        private Double DO;
        private Double WeissDoMgL;
        private Double BattV;
        private Double GGDOMgL;
        private Double BKDOMgL;
        private Boolean isAligned;
        private Long HeadID;

        public DODataResponse(Long ID, LocalDateTime date) {
            this.ID = ID;
            Date = date;
        }

        public DODataResponse(ProcessedDOData data) {
            ID = data.getID();
            Date = data.getDate();
            TempDegC = data.getTempDegC();
            DO = data.getDO();
            WeissDoMgL = data.getWeissDoMgL();
            BattV = data.getBattV();
            GGDOMgL = data.getGGDOMgL();
            BKDOMgL = data.getBKDOMgL();
            isAligned = data.getAligned();
            HeadID = data.getHeadID().getHeadID();
        }

        public Long getID() {
            return ID;
        }

        public void setID(Long ID) {
            this.ID = ID;
        }

        public LocalDateTime getDate() {
            return Date;
        }

        public void setDate(LocalDateTime date) {
            Date = date;
        }

        public Double getTempDegC() {
            return TempDegC;
        }

        public void setTempDegC(Double tempDegC) {
            TempDegC = tempDegC;
        }

        public Double getDO() {
            return DO;
        }

        public void setDO(Double DO) {
            this.DO = DO;
        }

        public Double getWeissDoMgL() {
            return WeissDoMgL;
        }

        public void setWeissDoMgL(Double weissDoMgL) {
            WeissDoMgL = weissDoMgL;
        }

        public Double getBattV() {
            return BattV;
        }

        public void setBattV(Double battV) {
            BattV = battV;
        }

        public Double getGGDOMgL() {
            return GGDOMgL;
        }

        public void setGGDOMgL(Double GGDOMgL) {
            this.GGDOMgL = GGDOMgL;
        }

        public Double getBKDOMgL() {
            return BKDOMgL;
        }

        public void setBKDOMgL(Double BKDOMgL) {
            this.BKDOMgL = BKDOMgL;
        }

        public Boolean getAligned() {
            return isAligned;
        }

        public void setAligned(Boolean aligned) {
            isAligned = aligned;
        }

        public Long getHeadID() {
            return HeadID;
        }

        public void setHeadID(Long headID) {
            HeadID = headID;
        }
    }
}
