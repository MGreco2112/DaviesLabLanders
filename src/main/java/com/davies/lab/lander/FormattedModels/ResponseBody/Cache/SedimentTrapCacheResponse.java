package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.SedimentTrapHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SedimentTrapCacheResponse {
    private Map<Long, SedimentTrapHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public SedimentTrapCacheResponse() {
    }

    public SedimentTrapCacheResponse(List<SedimentTrapHeadResponse> heads) {
        for (SedimentTrapHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, SedimentTrapHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, SedimentTrapHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
