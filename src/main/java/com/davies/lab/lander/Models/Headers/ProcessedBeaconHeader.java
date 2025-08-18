package com.davies.lab.lander.Models.Headers;

import com.davies.lab.lander.Models.Data.ProcessedBeaconData;
import com.davies.lab.lander.Models.Lander;

import javax.persistence.*;
import java.util.List;

@Entity
public class ProcessedBeaconHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HeadID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lander_id", referencedColumnName = "ASDBLanderID")
    private Lander LanderID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    private List<ProcessedBeaconData> data;

    public ProcessedBeaconHeader() {
    }

    private void addData(ProcessedBeaconData dataPoint) {
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

    public List<ProcessedBeaconData> getData() {
        return data;
    }

    public void setData(List<ProcessedBeaconData> data) {
        this.data = data;
    }
}
