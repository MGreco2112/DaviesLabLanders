package com.davies.lab.lander.Models.Data;

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
