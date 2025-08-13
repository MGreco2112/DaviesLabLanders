package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.ProcessedADCPData;

import java.util.List;

public class BulkADCPUploadRequest {
//    TODO: Possibly refactor this to use a different class
//    May need to create NewADCPDataRequest POJO that can be converted to Entity type at save
    private List<ProcessedADCPData> data;

    public BulkADCPUploadRequest() {
    }

    public BulkADCPUploadRequest(List<ProcessedADCPData> data) {
        this.data = data;
    }

    public List<ProcessedADCPData> getData() {
        return data;
    }

    public void setData(List<ProcessedADCPData> data) {
        this.data = data;
    }
}
