package com.davies.lab.lander.Models.Headers;

import com.davies.lab.lander.Models.Data.ProcessedCameraData;
import com.davies.lab.lander.Models.Lander;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProcessedCameraHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HeadID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lander_id", referencedColumnName = "ASDBLanderID")
    private Lander LanderID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    private List<ProcessedCameraData> data;

    public ProcessedCameraHeader() {
    }

    public void addData(ProcessedCameraData dataPoint) {
        data.add(dataPoint);
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander landerID) {
        LanderID = landerID;
    }

    public List<ProcessedCameraData> getData() {
        return data;
    }

    public void setData(List<ProcessedCameraData> data) {
        this.data = data;
    }
}
