package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Lander;

import java.util.HashSet;
import java.util.Set;

public class LanderResponseExternal extends BasicLanderResponseExternal{

    private CTDHeadResponseExternal ctdHead;
    private DOHeadResponseExternal doHead;
    private FLNTUHeadResponseExternal flntuHead;
    private ALBEXCTDHeadResponseExternal albexHead;


    public LanderResponseExternal (Lander lander) {
        super(lander);
    }

    public CTDHeadResponseExternal getCtdHead() {
        return ctdHead;
    }

    public void setCtdHead(CTDHeadResponseExternal ctdHead) {
        this.ctdHead = ctdHead;
    }

    public DOHeadResponseExternal getDoHead() {
        return doHead;
    }

    public void setDoHead(DOHeadResponseExternal doHead) {
        this.doHead = doHead;
    }

    public FLNTUHeadResponseExternal getFlntuHead() {
        return flntuHead;
    }

    public void setFlntuHead(FLNTUHeadResponseExternal flntuHead) {
        this.flntuHead = flntuHead;
    }

    public ALBEXCTDHeadResponseExternal getAlbexHead() {
        return albexHead;
    }

    public void setAlbexHead(ALBEXCTDHeadResponseExternal albexHead) {
        this.albexHead = albexHead;
    }
}
