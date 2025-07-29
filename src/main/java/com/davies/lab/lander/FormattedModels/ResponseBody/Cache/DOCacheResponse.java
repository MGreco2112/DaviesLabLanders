package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.DOHeadResponse;

import java.util.Date;
import java.util.List;

public class DOCacheResponse {

    private List<DOHeadResponse> heads;
    private Date cacheDate;

    public DOCacheResponse() {
    }

    public DOCacheResponse(List<DOHeadResponse> heads) {
        this.heads = heads;
        cacheDate = new Date();
    }

    public List<DOHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(List<DOHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
