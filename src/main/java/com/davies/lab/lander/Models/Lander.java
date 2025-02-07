package com.davies.lab.lander.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Lander {
    @Id
    private String ASDBLanderID;
    private String LanderPlatform;
    private String ASDBROVDiveID;

    public Lander() {

    }

    public Lander(String ASDBLanderID, String LanderPlatform, String ASDBROVDiveID) {
        this.ASDBLanderID = ASDBLanderID;
        this.LanderPlatform = LanderPlatform;
        this.ASDBROVDiveID = ASDBROVDiveID;
    }

    public String getASDBROVDiveID() {
        return ASDBROVDiveID;
    }

    public void setASDBROVDiveID(String ASDBROVDiveID) {
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
}
