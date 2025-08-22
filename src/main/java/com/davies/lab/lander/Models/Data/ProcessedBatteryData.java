package com.davies.lab.lander.Models.Data;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Battery_CSV_Request;
import com.davies.lab.lander.Models.Headers.ProcessedBatteryHeader;

import javax.persistence.*;

@Entity
public class ProcessedBatteryData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedBatteryHeader HeadID;

    public ProcessedBatteryData() {
    }

    public ProcessedBatteryData(Battery_CSV_Request request, ProcessedBatteryHeader head) {
//        TODO: deal with request once it's defined

        HeadID = head;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ProcessedBatteryHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedBatteryHeader headID) {
        HeadID = headID;
    }
}
