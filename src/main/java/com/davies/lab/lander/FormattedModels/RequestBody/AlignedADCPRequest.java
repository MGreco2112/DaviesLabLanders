package com.davies.lab.lander.FormattedModels.RequestBody;

public class AlignedADCPRequest {
    private Long rawID;
    private Double Vertical_Current_Speed_cm_s;
    private Double Horizontal_Current_Speed_cm_s;
    private Double Current_Direction;

    public AlignedADCPRequest(Long rawID, Double vertical_Current_Speed_cm_s, Double horizontal_Current_Speed_cm_s, Double current_Direction) {
        this.rawID = rawID;
        Vertical_Current_Speed_cm_s = vertical_Current_Speed_cm_s;
        Horizontal_Current_Speed_cm_s = horizontal_Current_Speed_cm_s;
        Current_Direction = current_Direction;
    }

    public Long getRawID() {
        return rawID;
    }

    public void setRawID(Long rawID) {
        this.rawID = rawID;
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
