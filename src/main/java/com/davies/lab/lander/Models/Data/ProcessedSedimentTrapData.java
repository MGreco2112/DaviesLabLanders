package com.davies.lab.lander.Models.Data;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.SedimentTrap_CSV_Request;
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

    public ProcessedSedimentTrapData(SedimentTrap_CSV_Request request, ProcessedSedimentTrapHeader head) {
//        TODO: update constructor with new fields once the CSV file is created
        HeadID = head;
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
