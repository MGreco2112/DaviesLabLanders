package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Lander;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class LanderResponseExternal {
    private String ASDBLanderID;
    private String LanderPlatform;
    private String ASDBROVDiveID;
    private LocalDateTime deploymentDateAndTime;
    private LocalDateTime recoveryDateAndTime;
    private Set<CTDHeadResponseExternal> CTDHeads = new HashSet<>();
    private Set<DOHeadResponseExternal> DOHeads = new HashSet<>();
    private Set<FLNTUHeadResponseExternal> FLNTUHeads = new HashSet<>();

    public LanderResponseExternal() {
    }

    public LanderResponseExternal (Lander lander) {
        ASDBLanderID = lander.getASDBLanderID();
        LanderPlatform = lander.getLanderPlatform();
        ASDBROVDiveID = lander.getASDBROVDiveID();
        deploymentDateAndTime = lander.getDeploymentDateAndTime();
        recoveryDateAndTime = lander.getRecoveryDateAndTime();
    }

    public void addCTDHead(CTDHeadResponseExternal newHead) {
        CTDHeads.add(newHead);
    }

    public void addDOHead(DOHeadResponseExternal newHead) { DOHeads.add(newHead); }

    public void addFLNTUHead(FLNTUHeadResponseExternal newHead) { FLNTUHeads.add(newHead); }

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

    public Set<CTDHeadResponseExternal> getCTDHeads() {
        return CTDHeads;
    }

    public void setCTDHeads(Set<CTDHeadResponseExternal> CTDHeads) {
        this.CTDHeads = CTDHeads;
    }

    public Set<DOHeadResponseExternal> getDOHeads() {
        return DOHeads;
    }

    public void setDOHeads(Set<DOHeadResponseExternal> DOHeads) {
        this.DOHeads = DOHeads;
    }
}
