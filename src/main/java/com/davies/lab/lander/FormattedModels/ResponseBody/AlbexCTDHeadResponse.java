package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.Data.ProcessedAlbexCTDData;
import com.davies.lab.lander.Models.Headers.ProcessedAlbexCTDHeader;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AlbexCTDHeadResponse {
    private Long HeadID;
    private String LanderID;
    private Integer dataPointCount;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<AlbexCTDDataResponse> data = new ArrayList<>();

    public AlbexCTDHeadResponse(Long headID, String landerID) {
        HeadID = headID;
        LanderID = landerID;
    }

    public AlbexCTDHeadResponse(ProcessedAlbexCTDHeader head) {
        HeadID = head.getHeadID();
        LanderID = head.getLanderID().getASDBLanderID();
        dataPointCount = head.getData().size();
        startTime = head.getLanderID().getDeploymentDateAndTime();
        endTime = head.getLanderID().getRecoveryDateAndTime();
    }

    public void createDataResponse(ProcessedAlbexCTDData AlbexData) {
        AlbexCTDDataResponse res = new AlbexCTDDataResponse(AlbexData.getID(), AlbexData.getDate());
        data.add(res);
    }

    public void createFullDataResponse(ProcessedAlbexCTDData dataPoint) {
        AlbexCTDDataResponse res = new AlbexCTDDataResponse(dataPoint);
        data.add(res);
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<AlbexCTDDataResponse> getData() {
        return data;
    }

    public void setData(List<AlbexCTDDataResponse> data) {
        this.data = data;
    }

    private class AlbexCTDDataResponse {
        private Long ID;
        private LocalDateTime Date;
        private Double Salinity;
        private Double Temperature;
        private Double Oxygen_ml_l;
        private Double OxygenSat_percent;
        private Double Turbidity_ntu;
        private Double Chla_ug_ml;
        private Double Pressure_db;
        private Integer Flag;
        private Boolean isAligned;
        private Long HeadID;

        public AlbexCTDDataResponse(Long ID, LocalDateTime date) {
            this.ID = ID;
            Date = date;
        }

        public AlbexCTDDataResponse(ProcessedAlbexCTDData data) {
            ID = data.getID();
            Date = data.getDate();
            Salinity = data.getSalinity();
            Temperature = data.getTemperature();
            Oxygen_ml_l = data.getOxygen_ml_l();
            OxygenSat_percent = data.getOxygenSat_percent();
            Turbidity_ntu = data.getTurbidity_ntu();
            Chla_ug_ml = data.getChla_ug_ml();
            Pressure_db = data.getPressure_db();
            Flag = data.getFlag();
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

        public Double getSalinity() {
            return Salinity;
        }

        public void setSalinity(Double salinity) {
            Salinity = salinity;
        }

        public Double getTemperature() {
            return Temperature;
        }

        public void setTemperature(Double temperature) {
            Temperature = temperature;
        }

        public Double getOxygen_ml_l() {
            return Oxygen_ml_l;
        }

        public void setOxygen_ml_l(Double oxygen_ml_l) {
            Oxygen_ml_l = oxygen_ml_l;
        }

        public Double getOxygenSat_percent() {
            return OxygenSat_percent;
        }

        public void setOxygenSat_percent(Double oxygenSat_percent) {
            OxygenSat_percent = oxygenSat_percent;
        }

        public Double getTurbidity_ntu() {
            return Turbidity_ntu;
        }

        public void setTurbidity_ntu(Double turbidity_ntu) {
            Turbidity_ntu = turbidity_ntu;
        }

        public Double getChla_ug_ml() {
            return Chla_ug_ml;
        }

        public void setChla_ug_ml(Double chla_ug_ml) {
            Chla_ug_ml = chla_ug_ml;
        }

        public Double getPressure_db() {
            return Pressure_db;
        }

        public void setPressure_db(Double pressure_db) {
            Pressure_db = pressure_db;
        }

        public Integer getFlag() {
            return Flag;
        }

        public void setFlag(Integer flag) {
            Flag = flag;
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
