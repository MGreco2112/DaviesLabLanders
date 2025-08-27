package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.BatteryHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BatteryCacheResponse {
    private Map<Long, BatteryHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public BatteryCacheResponse() {
    }

    public BatteryCacheResponse(List<BatteryHeadResponse> heads) {
        for (BatteryHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }

        cacheDate = new Date();
    }

    public Map<Long, BatteryHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, BatteryHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
