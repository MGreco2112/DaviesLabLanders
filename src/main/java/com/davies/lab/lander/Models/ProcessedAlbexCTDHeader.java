package com.davies.lab.lander.Models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ProcessedAlbexCTDHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long headID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lander_id", referencedColumnName = "ASDBLanderID")
    private Lander LanderID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    private List<ProcessedAlbexCTDData> data;

    public ProcessedAlbexCTDHeader() {
        data = new ArrayList<>();
    }

    public ProcessedAlbexCTDHeader(Lander landerID) {
        this.LanderID = landerID;
    }

    public ProcessedAlbexCTDHeader(Long headID, Lander landerID, List<ProcessedAlbexCTDData> data) {
        this.headID = headID;
        this.LanderID = landerID;
        this.data = data;
    }

    public Long getHeadID() {
        return headID;
    }

    public void setHeadID(Long headID) {
        this.headID = headID;
    }

    public Lander getLanderID() {
        return LanderID;
    }

    public void setLanderID(Lander LanderID) {
        this.LanderID = LanderID;
    }

    public List<ProcessedAlbexCTDData> getData() {
        return data;
    }

    public void setData(List<ProcessedAlbexCTDData> data) {
        this.data = data;
    }
}
