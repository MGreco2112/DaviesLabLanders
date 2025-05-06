package com.davies.lab.lander.FormattedModels.ResponseBody;

public class TotalDataResponse {
    private Integer numberOfFiles;

    public TotalDataResponse() {
    }

    public TotalDataResponse(Integer numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }

    public Integer getNumberOfFiles() {
        return numberOfFiles;
    }

    public void setNumberOfFiles(Integer numberOfFiles) {
        this.numberOfFiles = numberOfFiles;
    }
}
