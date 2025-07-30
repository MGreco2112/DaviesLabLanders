package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.CTDHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CTDCacheResponse {
    private Map<Long, CTDHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public CTDCacheResponse() {
    }

    public CTDCacheResponse(List<CTDHeadResponse> heads) {
        for (CTDHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, CTDHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, CTDHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
