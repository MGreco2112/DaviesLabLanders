package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.*;

import java.time.LocalDateTime;
import java.util.Set;

public class UpdateLanderRequest {
    private String LanderPlatform;
    private String ASDBROBDiveID;
    private LocalDateTime deploymentDateAndTime;
    private LocalDateTime recoveryDateAndTime;
    private ProcessedCTDHead CTDHead;
    private ProcessedDOHead DOHead;
    private ProcessedFLNTUHead FLNTUHead;
    private ProcessedAlbexCTDHeader AlbexHead;
    private ProcessedADCPHead ADCPHead;

    public UpdateLanderRequest() {
    }

    public UpdateLanderRequest(String landerPlatform, String ASDBROBDiveID, LocalDateTime deploymentDateAndTime, LocalDateTime recoveryDateAndTime, ProcessedCTDHead CTDHead, ProcessedDOHead DOHead, ProcessedFLNTUHead FLNTUHead, ProcessedAlbexCTDHeader albexHead, ProcessedADCPHead ADCPHead) {
        LanderPlatform = landerPlatform;
        this.ASDBROBDiveID = ASDBROBDiveID;
        this.deploymentDateAndTime = deploymentDateAndTime;
        this.recoveryDateAndTime = recoveryDateAndTime;
        this.CTDHead = CTDHead;
        this.DOHead = DOHead;
        this.FLNTUHead = FLNTUHead;
        AlbexHead = albexHead;
        ADCPHead = ADCPHead;
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

    public ProcessedAlbexCTDHeader getAlbexHead() {
        return AlbexHead;
    }

    public void setAlbexHead(ProcessedAlbexCTDHeader albexHead) {
        AlbexHead = albexHead;
    }

    public ProcessedADCPHead getADCPHead() {
        return ADCPHead;
    }

    public void setADCPHead(ProcessedADCPHead ADCPHead) {
        this.ADCPHead = ADCPHead;
    }
}
