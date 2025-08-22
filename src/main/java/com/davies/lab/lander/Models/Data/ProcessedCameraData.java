package com.davies.lab.lander.Models.Data;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.Camera_CSV_Request;
import com.davies.lab.lander.Models.Headers.ProcessedCameraHeader;

import javax.persistence.*;

@Entity
public class ProcessedCameraData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedCameraHeader HeadID;

    public ProcessedCameraData() {
    }

    public ProcessedCameraData(Camera_CSV_Request request, ProcessedCameraHeader head) {
//        TODO: Update with full CSV request once built
        HeadID = head;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public ProcessedCameraHeader getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedCameraHeader headID) {
        HeadID = headID;
    }
}
