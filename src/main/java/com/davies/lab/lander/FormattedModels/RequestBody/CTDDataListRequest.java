package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies.CTD_CSV_Request;

import java.util.List;

public class CTDDataListRequest {
    private List<CTD_CSV_Request> data;

    public CTDDataListRequest() {
    }

    public CTDDataListRequest(List<CTD_CSV_Request> data) {
        this.data = data;
    }

    public List<CTD_CSV_Request> getData() {
        return data;
    }

    public void setData(List<CTD_CSV_Request> data) {
        this.data = data;
    }
}
