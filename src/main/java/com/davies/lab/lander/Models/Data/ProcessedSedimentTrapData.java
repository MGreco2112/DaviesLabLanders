package com.davies.lab.lander.Models.Data;

import com.davies.lab.lander.Models.Headers.ProcessedSedimentTrapHeader;

import javax.persistence.*;

@Entity
public class ProcessedSedimentTrapData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedSedimentTrapHeader HeadID;

    public ProcessedSedimentTrapData() {
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public ProcessedSedimentTrapHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedSedimentTrapHeader headID) {
        HeadID = headID;
    }
}
