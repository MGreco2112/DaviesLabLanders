package com.davies.lab.lander.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ProcessedADCPHead {
//    TODO: Update this model with incoming metadata from Andy and Jane

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long HeadID;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Lander_id", referencedColumnName = "ASDBLanderID")
    private Lander LanderID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    private List<ProcessedADCPData> data;

    public ProcessedADCPHead() {
        data = new ArrayList<>();
    }

    public ProcessedADCPHead(Lander landerID) {
        LanderID = landerID;
    }

    public ProcessedADCPHead(Long headID, Lander landerID, ArrayList<ProcessedADCPData> data) {
        HeadID = headID;
        LanderID = landerID;
        this.data = data;
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

    public List<ProcessedADCPData> getData() {
        return data;
    }

    public void setData(List<ProcessedADCPData> data) {
        this.data = data;
    }
}
