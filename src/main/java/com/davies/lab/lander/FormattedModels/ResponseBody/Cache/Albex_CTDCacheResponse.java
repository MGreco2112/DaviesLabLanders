package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.AlbexCTDHeadResponse;

import java.util.Date;
import java.util.List;

public class Albex_CTDCacheResponse {
    private List<AlbexCTDHeadResponse> heads;
    private Date cacheDate;

    public Albex_CTDCacheResponse() {
    }

    public Albex_CTDCacheResponse(List<AlbexCTDHeadResponse> heads) {
        this.heads = heads;
        cacheDate = new Date();
    }

    public List<AlbexCTDHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(List<AlbexCTDHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
