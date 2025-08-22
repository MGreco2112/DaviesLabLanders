package com.davies.lab.lander.Models.Data;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Beacon_CSV_Request;
import com.davies.lab.lander.Models.Headers.ProcessedBeaconHeader;

import javax.persistence.*;

@Entity
public class ProcessedBeaconData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedBeaconHeader HeadID;

    public ProcessedBeaconData() {
    }

    public ProcessedBeaconData(Beacon_CSV_Request request, ProcessedBeaconHeader head) {
//        TODO: build out Request elements once CSV comes around
        HeadID = head;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public ProcessedBeaconHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedBeaconHeader headID) {
        HeadID = headID;
    }
}
