package com.davies.lab.lander.FormattedModels.ResponseBody;

public class DataProgressResponse {
    private boolean isPercentage;
    private Integer fileCount;
    private Double percentage;

    public DataProgressResponse() {
    }

    public DataProgressResponse(Integer fileCount) {
        this.fileCount = fileCount;
        isPercentage = false;
    }

    public DataProgressResponse(Double percentage) {
        this.percentage = percentage;
        isPercentage = true;
    }

    public boolean getIsPercentage() {
        return isPercentage;
    }

    public void setIsPercentage(boolean percentage) {
        isPercentage = percentage;
    }

    public Integer getFileCount() {
        return fileCount;
    }

    public void setFileCount(Integer fileCount) {
        this.fileCount = fileCount;
    }

    public Double getPercentage() {
        return percentage;
    }

    public void setPercentage(Double percentage) {
        this.percentage = percentage;
    }
}
