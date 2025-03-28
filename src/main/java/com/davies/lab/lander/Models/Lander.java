package com.davies.lab.lander.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Lander {
    @Id
    private String ASDBLanderID;
    private String LanderPlatform;
    private String ASDBROVDiveID;
    private LocalDateTime deploymentDateAndTime;
    private LocalDateTime recoveryDateAndTime;
    @OneToMany(mappedBy = "LanderID", fetch = FetchType.LAZY)
    private Set<ProcessedCTDHead> CTDHeads;
    @OneToMany(mappedBy = "LanderID", fetch = FetchType.LAZY)
    private Set<ProcessedDOHead> DOHeads;
    @OneToMany(mappedBy = "LanderID", fetch = FetchType.LAZY)
    private Set<ProcessedFLNTUHead> FLNTUHeads;

    public Lander() {
    }

    public Lander(String ASDBLanderID, String landerPlatform, String ASDBROVDiveID, LocalDateTime deploymentDateAndTime, LocalDateTime recoveryDateAndTime) {
        this.ASDBLanderID = ASDBLanderID;
        LanderPlatform = landerPlatform;
        this.ASDBROVDiveID = ASDBROVDiveID;
        this.deploymentDateAndTime = deploymentDateAndTime;
        this.recoveryDateAndTime = recoveryDateAndTime;
    }

    public Lander(String ASDBLanderID, String landerPlatform, String ASDBROVDiveID) {
        this.ASDBLanderID = ASDBLanderID;
        LanderPlatform = landerPlatform;
        this.ASDBROVDiveID = ASDBROVDiveID;
    }

    public String getASDBLanderID() {
        return ASDBLanderID;
    }

    public void setASDBLanderID(String ASDBLanderID) {
        this.ASDBLanderID = ASDBLanderID;
    }

    public String getLanderPlatform() {
        return LanderPlatform;
    }

    public void setLanderPlatform(String landerPlatform) {
        LanderPlatform = landerPlatform;
    }

    public String getASDBROVDiveID() {
        return ASDBROVDiveID;
    }

    public void setASDBROVDiveID(String ASDBROVDiveID) {
        this.ASDBROVDiveID = ASDBROVDiveID;
    }

    public LocalDateTime getDeploymentDateAndTime() {
        return deploymentDateAndTime;
    }

    public void setDeploymentDateAndTime(LocalDateTime deploymentDateAndTime) {
        this.deploymentDateAndTime = deploymentDateAndTime;
    }

    public LocalDateTime getRecoveryDateAndTime() {
        return recoveryDateAndTime;
    }

    public void setRecoveryDateAndTime(LocalDateTime recoveryDateAndTime) {
        this.recoveryDateAndTime = recoveryDateAndTime;
    }

    public Set<ProcessedCTDHead> getCTDHeads() {
        return CTDHeads;
    }

    public void setCTDHeads(Set<ProcessedCTDHead> CTDHeads) {
        this.CTDHeads = CTDHeads;
    }

    public Set<ProcessedDOHead> getDOHeads() {
        return DOHeads;
    }

    public void setDOHeads(Set<ProcessedDOHead> DOHeads) {
        this.DOHeads = DOHeads;
    }

    public Set<ProcessedFLNTUHead> getFLNTUHeads() {
        return FLNTUHeads;
    }

    public void setFLNTUHeads(Set<ProcessedFLNTUHead> FLNTUHeads) {
        this.FLNTUHeads = FLNTUHeads;
    }
}
