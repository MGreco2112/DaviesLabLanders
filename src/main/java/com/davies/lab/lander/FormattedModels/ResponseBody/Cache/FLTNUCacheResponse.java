package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.FLNTUHeadResponse;

import java.util.Date;
import java.util.List;

public class FLTNUCacheResponse {
    private List<FLNTUHeadResponse> heads;
    private Date cacheDate;

    public FLTNUCacheResponse() {
    }

    public FLTNUCacheResponse(List<FLNTUHeadResponse> heads) {
        this.heads = heads;
        cacheDate = new Date();
    }

    public List<FLNTUHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(List<FLNTUHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
