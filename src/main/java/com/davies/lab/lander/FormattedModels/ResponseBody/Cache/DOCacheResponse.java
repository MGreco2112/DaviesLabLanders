package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.DOHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DOCacheResponse {

    private Map<Long, DOHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public DOCacheResponse() {
    }

    public DOCacheResponse(List<DOHeadResponse> heads) {
        for (DOHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, DOHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, DOHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
