package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.BeaconHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BeaconCacheResponse {
    private Map<Long, BeaconHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public BeaconCacheResponse() {
    }

    public BeaconCacheResponse(List<BeaconHeadResponse> heads) {
        for (BeaconHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, BeaconHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, BeaconHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
