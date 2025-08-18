package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Data.ProcessedADCPData;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ADCPDataResponseExternal {
    private Long ID;
    private LocalDateTime Date;
    private Double Vertical_Current_Speed_cm_s;
    private Double Horizontal_Current_Speed_cm_s;
    private Double Current_Direction;
    private Long HeadID;

    public ADCPDataResponseExternal() {
    }

    public ADCPDataResponseExternal(ProcessedADCPData data) {
        ID = data.getID();
        Date = data.getDate();
        Vertical_Current_Speed_cm_s = data.getAlignedData().getVertical_Current_Speed_cm_s();
        Horizontal_Current_Speed_cm_s = data.getAlignedData().getHorizontal_Current_Speed_cm_s();
        Current_Direction = data.getAlignedData().getCurrent_Direction();
        HeadID = data.getHeadID().getHeadID();
    }

    public static List<ADCPDataResponseExternal> createBulkResponses(List<ProcessedADCPData> headData) {
        List<ADCPDataResponseExternal> newList = new ArrayList<>();

        for (ProcessedADCPData data : headData) {
            newList.add(new ADCPDataResponseExternal(data));
        }

        return newList;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
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

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }
}
