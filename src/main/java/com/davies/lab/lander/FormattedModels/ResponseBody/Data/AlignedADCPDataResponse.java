package com.davies.lab.lander.FormattedModels.ResponseBody.Data;

import com.davies.lab.lander.Models.Data.ProcessedADCPData;

import java.time.LocalDateTime;

public class AlignedADCPDataResponse {
    private Long ID;
    private LocalDateTime date;
    private Double Vertical_Current_Speed_cm_s;
    private Double Horizontal_Current_Speed_cm_s;
    private Double Current_Direction;

    public AlignedADCPDataResponse() {
    }

    public AlignedADCPDataResponse(Long ID, LocalDateTime date, Double vertical_Current_Speed_cm_s, Double horizontal_Current_Speed_cm_s, Double current_Direction) {
        this.ID = ID;
        this.date = date;
        Vertical_Current_Speed_cm_s = vertical_Current_Speed_cm_s;
        Horizontal_Current_Speed_cm_s = horizontal_Current_Speed_cm_s;
        Current_Direction = current_Direction;
    }

    public AlignedADCPDataResponse(ProcessedADCPData processedData) {
        ID = processedData.getID();
        date = processedData.getDate();
        Vertical_Current_Speed_cm_s = processedData.getAlignedData().getVertical_Current_Speed_cm_s();
        Horizontal_Current_Speed_cm_s = processedData.getAlignedData().getHorizontal_Current_Speed_cm_s();
        Current_Direction = processedData.getAlignedData().getCurrent_Direction();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
}
