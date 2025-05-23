package com.davies.lab.lander.Models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class ProcessedAlbexCTDHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long headID;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "lander_id", referencedColumnName = "ASDBLanderID")
    private Lander landerID;
    @OneToMany(mappedBy = "HeadID", fetch = FetchType.LAZY)
    private Set<ProcessedAlbexCTDData> data;

    public ProcessedAlbexCTDHeader() {
        data = new HashSet<>();
    }

    public ProcessedAlbexCTDHeader(Lander landerID) {
        this.landerID = landerID;
    }

    public ProcessedAlbexCTDHeader(Long headID, Lander landerID, Set<ProcessedAlbexCTDData> data) {
        this.headID = headID;
        this.landerID = landerID;
        this.data = data;
    }

    public Long getHeadID() {
        return headID;
    }

    public void setHeadID(Long headID) {
        this.headID = headID;
    }

    public Lander getLanderID() {
        return landerID;
    }

    public void setLanderID(Lander landerID) {
        this.landerID = landerID;
    }

    public Set<ProcessedAlbexCTDData> getData() {
        return data;
    }

    public void setData(Set<ProcessedAlbexCTDData> data) {
        this.data = data;
    }
}
