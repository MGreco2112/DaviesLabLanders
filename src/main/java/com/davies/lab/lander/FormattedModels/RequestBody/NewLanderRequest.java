package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.HelperClasses.StringFormatting;

import java.time.LocalDateTime;

public class NewLanderRequest {
    private String ASDBLanderID;
    private String LanderPlatform;
    private String ASDBROVDiveID;
    private String deploymentDateAndTime;
    private String recoveryDateAndTime;

    public NewLanderRequest() {
    }

    public NewLanderRequest(String ASDBLanderID, String landerPlatform, String ASDBROVDiveID, String deploymentDateAndTime, String recoveryDateAndTime) {
        this.ASDBLanderID = ASDBLanderID;
        LanderPlatform = landerPlatform;
        this.ASDBROVDiveID = ASDBROVDiveID;
        this.deploymentDateAndTime = deploymentDateAndTime;
        this.recoveryDateAndTime = recoveryDateAndTime;
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

    public String getDeploymentDateAndTime() {
        return deploymentDateAndTime;
    }

    public void setDeploymentDateAndTime(String deploymentDateAndTime) {
        this.deploymentDateAndTime = deploymentDateAndTime;
    }

    public String getRecoveryDateAndTime() {
        return recoveryDateAndTime;
    }

    public void setRecoveryDateAndTime(String recoveryDateAndTime) {
        this.recoveryDateAndTime = recoveryDateAndTime;
    }
}
