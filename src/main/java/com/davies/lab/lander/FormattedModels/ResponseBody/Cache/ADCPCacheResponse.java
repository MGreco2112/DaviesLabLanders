package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.ADCPHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ADCPCacheResponse {
    private Map<Long, ADCPHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public ADCPCacheResponse() {
    }

    public ADCPCacheResponse(List<ADCPHeadResponse> heads) {
        for (ADCPHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, ADCPHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, ADCPHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
