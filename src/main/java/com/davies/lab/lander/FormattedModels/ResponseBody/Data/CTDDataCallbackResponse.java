package com.davies.lab.lander.FormattedModels.ResponseBody.Data;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.CTD_CSV_Request;

import java.util.List;

public class CTDDataCallbackResponse {
    private Integer listSize;
    private Long headID;
    private List<CTD_CSV_Request> data;

    public CTDDataCallbackResponse() {
    }

    public CTDDataCallbackResponse(Integer listSize, Long headID, List<CTD_CSV_Request> data) {
        this.listSize = listSize;
        this.headID = headID;
        this.data = data;
    }

    public Integer getListSize() {
        return listSize;
    }

    public void setListSize(Integer listSize) {
        this.listSize = listSize;
    }

    public Long getHeadID() {
        return headID;
    }

    public void setHeadID(Long headID) {
        this.headID = headID;
    }

    public List<CTD_CSV_Request> getData() {
        return data;
    }

    public void setData(List<CTD_CSV_Request> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{ \"headID\": " + headID + ", \"listSize\": " + listSize + ", \"data\": " + data + "}";
    }
}
