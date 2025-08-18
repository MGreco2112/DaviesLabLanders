package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.AlbexCTDHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Albex_CTDCacheResponse {
    private Map<Long, AlbexCTDHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public Albex_CTDCacheResponse() {
    }

    public Albex_CTDCacheResponse(List<AlbexCTDHeadResponse> heads) {
        for (AlbexCTDHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, AlbexCTDHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, AlbexCTDHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
