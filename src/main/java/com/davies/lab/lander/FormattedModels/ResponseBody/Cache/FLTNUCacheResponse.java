package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.FLNTUHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FLTNUCacheResponse {
    private Map<Long, FLNTUHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public FLTNUCacheResponse() {
    }

    public FLTNUCacheResponse(List<FLNTUHeadResponse> heads) {
        for (FLNTUHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, FLNTUHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, FLNTUHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
