package com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard;

public class DashboardResponse {
    private Integer landerSetCount;
    private Integer totalDataPoints;
    private Integer totalAlignedDataPoints;

    public DashboardResponse() {
    }

    public DashboardResponse(Integer landerSetCount, Integer totalDataPoints, Integer totalAlignedDataPoints) {
        this.landerSetCount = landerSetCount;
        this.totalDataPoints = totalDataPoints;
        this.totalAlignedDataPoints = totalAlignedDataPoints;
    }

    public Integer getLanderSetCount() {
        return landerSetCount;
    }

    public void setLanderSetCount(Integer landerSetCount) {
        this.landerSetCount = landerSetCount;
    }

    public Integer getTotalDataPoints() {
        return totalDataPoints;
    }

    public void setTotalDataPoints(Integer totalDataPoints) {
        this.totalDataPoints = totalDataPoints;
    }

    public Integer getTotalAlignedDataPoints() {
        return totalAlignedDataPoints;
    }

    public void setTotalAlignedDataPoints(Integer totalAlignedDataPoints) {
        this.totalAlignedDataPoints = totalAlignedDataPoints;
    }
}
