package com.davies.lab.lander.FormattedModels.ResponseBody;

import java.util.List;

public class LatestLandersResponse {
    List<LanderResponse> landers;

    public LatestLandersResponse() {
    }

    public LatestLandersResponse(List<LanderResponse> landers) {
        this.landers = landers;
    }

    public List<LanderResponse> getLanders() {
        return landers;
    }

    public void setLanders(List<LanderResponse> landers) {
        this.landers = landers;
    }
}
