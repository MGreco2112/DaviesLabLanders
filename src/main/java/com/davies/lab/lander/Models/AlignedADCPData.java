package com.davies.lab.lander.Models;

import com.davies.lab.lander.FormattedModels.RequestBody.AlignedADCPRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class AlignedADCPData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    private Double Vertical_Current_Speed_cm_s;
    private Double Horizontal_Current_Speed_cm_s;
    private Double Current_Direction;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "raw_data", referencedColumnName = "ID")
    @JsonIgnore
    private ProcessedADCPData rawData;

    public AlignedADCPData() {
    }

    public AlignedADCPData(Double vertical_Current_Speed_cm_s, Double horizontal_Current_Speed_cm_s, Double current_Direction, ProcessedADCPData rawData) {
        Vertical_Current_Speed_cm_s = vertical_Current_Speed_cm_s;
        Horizontal_Current_Speed_cm_s = horizontal_Current_Speed_cm_s;
        Current_Direction = current_Direction;
        this.rawData = rawData;
    }

    public AlignedADCPData(Long ID, Double vertical_Current_Speed_cm_s, Double horizontal_Current_Speed_cm_s, Double current_Direction, ProcessedADCPData rawData) {
        this.ID = ID;
        Vertical_Current_Speed_cm_s = vertical_Current_Speed_cm_s;
        Horizontal_Current_Speed_cm_s = horizontal_Current_Speed_cm_s;
        Current_Direction = current_Direction;
        this.rawData = rawData;
    }

    public AlignedADCPData(AlignedADCPRequest request) {
        Vertical_Current_Speed_cm_s = request.getVertical_Current_Speed_cm_s();
        Horizontal_Current_Speed_cm_s = request.getHorizontal_Current_Speed_cm_s();
        Current_Direction = request.getCurrent_Direction();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Double getVertical_Current_Speed_cm_s() {
        return Vertical_Current_Speed_cm_s;
    }

    public void setVertical_Current_Speed_cm_s(Double vertical_Current_Speed_cm_s) {
        Vertical_Current_Speed_cm_s = vertical_Current_Speed_cm_s;
    }

    public Double getHorizontal_Current_Speed_cm_s() {
        return Horizontal_Current_Speed_cm_s;
    }

    public void setHorizontal_Current_Speed_cm_s(Double horizontal_Current_Speed_cm_s) {
        Horizontal_Current_Speed_cm_s = horizontal_Current_Speed_cm_s;
    }

    public Double getCurrent_Direction() {
        return Current_Direction;
    }

    public void setCurrent_Direction(Double current_Direction) {
        Current_Direction = current_Direction;
    }

    public ProcessedADCPData getRawData() {
        return rawData;
    }

    public void setRawData(ProcessedADCPData rawData) {
        this.rawData = rawData;
    }
}
