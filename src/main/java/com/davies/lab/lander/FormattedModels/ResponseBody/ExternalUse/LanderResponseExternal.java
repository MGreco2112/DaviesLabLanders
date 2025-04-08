package com.davies.lab.lander.FormattedModels.ResponseBody.ExternalUse;

import com.davies.lab.lander.Models.Lander;

import java.util.HashSet;
import java.util.Set;

public class LanderResponseExternal extends BasicLanderResponseExternal{

    private Set<CTDHeadResponseExternal> CTDHeads = new HashSet<>();
    private Set<DOHeadResponseExternal> DOHeads = new HashSet<>();
    private Set<FLNTUHeadResponseExternal> FLNTUHeads = new HashSet<>();


    public LanderResponseExternal (Lander lander) {
        super(lander);
    }

    public void addCTDHead(CTDHeadResponseExternal newHead) {
        CTDHeads.add(newHead);
    }

    public void addDOHead(DOHeadResponseExternal newHead) { DOHeads.add(newHead); }

    public void addFLNTUHead(FLNTUHeadResponseExternal newHead) { FLNTUHeads.add(newHead); }

    public Set<CTDHeadResponseExternal> getCTDHeads() {
        return CTDHeads;
    }

    public void setCTDHeads(Set<CTDHeadResponseExternal> CTDHeads) {
        this.CTDHeads = CTDHeads;
    }

    public Set<DOHeadResponseExternal> getDOHeads() {
        return DOHeads;
    }

    public void setDOHeads(Set<DOHeadResponseExternal> DOHeads) {
        this.DOHeads = DOHeads;
    }

    public Set<FLNTUHeadResponseExternal> getFLNTUHeads() {
        return FLNTUHeads;
    }

    public void setFLNTUHeads(Set<FLNTUHeadResponseExternal> FLNTUHeads) {
        this.FLNTUHeads = FLNTUHeads;
    }
}
