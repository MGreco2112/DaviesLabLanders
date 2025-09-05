package com.davies.lab.lander.Models.Headers;

import com.davies.lab.lander.Models.Data.ProcessedBatteryData;
import com.davies.lab.lander.Models.Lander;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class ProcessedBatteryHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HeadID;
    private LocalDateTime StartTime;
    private LocalDateTime EndTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lander_id", referencedColumnName = "ASDBLanderID")
    private Lander LanderID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    private List<ProcessedBatteryData> data;

    public ProcessedBatteryHeader() {
    }

    private void addData(ProcessedBatteryData dataPoint) {
        data.add(dataPoint);
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
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

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public List<ProcessedBatteryData> getData() {
        return data;
    }

    public void setData(List<ProcessedBatteryData> data) {
        this.data = data;
    }
}
