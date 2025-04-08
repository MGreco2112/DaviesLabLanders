package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.ProcessedCTDHead;
import com.davies.lab.lander.Models.ProcessedDOHead;
import com.davies.lab.lander.Models.ProcessedFLNTUHead;

import java.time.LocalDateTime;
import java.util.Set;

public class UpdateLanderRequest {
    private String LanderPlatform;
    private String ASDBROBDiveID;
    private LocalDateTime deploymentDateAndTime;
    private LocalDateTime recoveryDateAndTime;
    private Set<ProcessedCTDHead> CTDHeads;
    private Set<ProcessedDOHead> DOHeads;
    private Set<ProcessedFLNTUHead> FLNTUHeads;

    public UpdateLanderRequest() {
    }

    public UpdateLanderRequest(String landerPlatform, String ASDBROBDiveID, LocalDateTime deploymentDateAndTime, LocalDateTime recoveryDateAndTime, Set<ProcessedCTDHead> CTDHeads, Set<ProcessedDOHead> DOHeads, Set<ProcessedFLNTUHead> FLNTUHeads) {
        LanderPlatform = landerPlatform;
        this.ASDBROBDiveID = ASDBROBDiveID;
        this.deploymentDateAndTime = deploymentDateAndTime;
        this.recoveryDateAndTime = recoveryDateAndTime;
        this.CTDHeads = CTDHeads;
        this.DOHeads = DOHeads;
        this.FLNTUHeads = FLNTUHeads;
    }

    public String getLanderPlatform() {
        return LanderPlatform;
    }

    public void setLanderPlatform(String landerPlatform) {
        LanderPlatform = landerPlatform;
    }

    public String getASDBROBDiveID() {
        return ASDBROBDiveID;
    }

    public void setASDBROBDiveID(String ASDBROBDiveID) {
        this.ASDBROBDiveID = ASDBROBDiveID;
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
