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
    @OneToOne(mappedBy = "LanderID")
    private ProcessedCTDHead CTDHead;
    @OneToOne(mappedBy = "LanderID")
    private ProcessedDOHead DOHead;
    @OneToOne(mappedBy = "LanderID")
    private ProcessedFLNTUHead FLNTUHead;

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

    public ProcessedCTDHead getCTDHead() {
        return CTDHead;
    }

    public void setCTDHead(ProcessedCTDHead CTDHead) {
        this.CTDHead = CTDHead;
    }

    public ProcessedDOHead getDOHead() {
        return DOHead;
    }

    public void setDOHead(ProcessedDOHead DOHead) {
        this.DOHead = DOHead;
    }

    public ProcessedFLNTUHead getFLNTUHead() {
        return FLNTUHead;
    }

    public void setFLNTUHead(ProcessedFLNTUHead FLNTUHead) {
        this.FLNTUHead = FLNTUHead;
    }
}
