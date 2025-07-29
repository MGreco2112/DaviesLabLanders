package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.ADCPHeadResponse;

import java.util.Date;
import java.util.List;

public class ADCPCacheResponse {
    private List<ADCPHeadResponse> heads;
    private Date cacheDate;

    public ADCPCacheResponse() {
    }

    public ADCPCacheResponse(List<ADCPHeadResponse> heads) {
        this.heads = heads;
        cacheDate = new Date();
    }

    public List<ADCPHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(List<ADCPHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
