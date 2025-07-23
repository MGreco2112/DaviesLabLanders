package com.davies.lab.lander.FormattedModels.ResponseBody.Dashboard;

import java.util.Map;

public class CompletedDashboard {
    private DashboardResponse dashboard;
    private Map<Integer, Integer> pointsPerYear;

    public CompletedDashboard() {
    }

    public CompletedDashboard(DashboardResponse dashboard, Map<Integer, Integer> pointsPerYear) {
        this.dashboard = dashboard;
        this.pointsPerYear = pointsPerYear;
    }

    public DashboardResponse getDashboard() {
        return dashboard;
    }

    public void setDashboard(DashboardResponse dashboard) {
        this.dashboard = dashboard;
    }

    public Map<Integer, Integer> getPointsPerYear() {
        return pointsPerYear;
    }

    public void setPointsPerYear(Map<Integer, Integer> pointsPerYear) {
        this.pointsPerYear = pointsPerYear;
    }
}
