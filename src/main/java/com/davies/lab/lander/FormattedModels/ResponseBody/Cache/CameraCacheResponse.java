package com.davies.lab.lander.FormattedModels.ResponseBody.Cache;

import com.davies.lab.lander.FormattedModels.ResponseBody.Head.CameraHeadResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CameraCacheResponse {
    private Map<Long, CameraHeadResponse> heads = new HashMap<>();
    private Date cacheDate;

    public CameraCacheResponse() {
    }

    public CameraCacheResponse(List<CameraHeadResponse> heads) {
        for (CameraHeadResponse head : heads) {
            this.heads.put(head.getHeadID(), head);
        }
        cacheDate = new Date();
    }

    public Map<Long, CameraHeadResponse> getHeads() {
        return heads;
    }

    public void setHeads(Map<Long, CameraHeadResponse> heads) {
        this.heads = heads;
    }

    public Date getCacheDate() {
        return cacheDate;
    }

    public void setCacheDate(Date cacheDate) {
        this.cacheDate = cacheDate;
    }
}
