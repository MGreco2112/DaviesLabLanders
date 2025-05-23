package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.ProcessedAlbexCTDData;

import java.util.Set;

public class UpdateAlbexCTDHeaderRequest {
    private Set<ProcessedAlbexCTDData> data;

    public UpdateAlbexCTDHeaderRequest() {
    }

    public UpdateAlbexCTDHeaderRequest(Set<ProcessedAlbexCTDData> data) {
        this.data = data;
    }

    public Set<ProcessedAlbexCTDData> getData() {
        return data;
    }

    public void setData(Set<ProcessedAlbexCTDData> data) {
        this.data = data;
    }
}
