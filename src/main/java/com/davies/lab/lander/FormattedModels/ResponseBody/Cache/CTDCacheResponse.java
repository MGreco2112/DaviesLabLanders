package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.CTDHeadResponse;

import java.util.Date;
import java.util.List;

public class CTDCacheResponse {
    private List<CTDHeadResponse> heads;
    private Date cacheDate;

    public CTDCacheResponse() {
    }

    public CTDCacheResponse(List<CTDHeadResponse> heads) {
        this.heads = heads;
        cacheDate = new Date();
    }

    public List<CTDHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(List<CTDHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
