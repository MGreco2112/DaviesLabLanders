package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Data.ProcessedADCPData;

import java.util.Set;

public class UpdateADCPHeaderRequest {
    private Set<ProcessedADCPData> data;

    public UpdateADCPHeaderRequest() {
    }

    public UpdateADCPHeaderRequest(Set<ProcessedADCPData> data) {
        this.data = data;
    }

    public Set<ProcessedADCPData> getData() {
        return data;
    }

    public void setData(Set<ProcessedADCPData> data) {
        this.data = data;
    }
}
