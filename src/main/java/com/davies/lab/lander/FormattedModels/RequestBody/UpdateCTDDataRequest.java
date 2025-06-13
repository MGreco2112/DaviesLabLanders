package com.davies.lab.lander.FormattedModels.RequestBody;

import com.davies.lab.lander.Models.AlignedCTDData;
import com.davies.lab.lander.Models.ProcessedCTDHead;

import java.time.LocalDateTime;

public class UpdateCTDDataRequest {
    private LocalDateTime Date;
    private Double TempDegC;
    private Double Sal;
    private Double CondMsCm;
    private Double Ec25UsCm;
    private Double BattV;
    private Boolean isAligned;
    private AlignedCTDData alignedData;
    private ProcessedCTDHead HeadID;

    public UpdateCTDDataRequest() {
    }

    public UpdateCTDDataRequest(LocalDateTime date, Double tempDegC, Double sal, Double condMsCm, Double ec25UsCm, Double battV, Boolean isAligned, AlignedCTDData alignedData, ProcessedCTDHead headID) {
        Date = date;
        TempDegC = tempDegC;
        Sal = sal;
        CondMsCm = condMsCm;
        Ec25UsCm = ec25UsCm;
        BattV = battV;
        this.isAligned = isAligned;
        this.alignedData = alignedData;
        HeadID = headID;
    }

    public LocalDateTime getDate() {
        return Date;
    }

    public void setDate(LocalDateTime date) {
        Date = date;
    }

    public Double getTempDegC() {
        return TempDegC;
    }

    public void setTempDegC(Double tempDegC) {
        TempDegC = tempDegC;
    }

    public Double getSal() {
        return Sal;
    }

    public void setSal(Double sal) {
        Sal = sal;
    }

    public Double getCondMsCm() {
        return CondMsCm;
    }

    public void setCondMsCm(Double condMsCm) {
        CondMsCm = condMsCm;
    }

    public Double getEc25UsCm() {
        return Ec25UsCm;
    }

    public void setEc25UsCm(Double ec25UsCm) {
        Ec25UsCm = ec25UsCm;
    }

    public Double getBattV() {
        return BattV;
    }

    public void setBattV(Double battV) {
        BattV = battV;
    }

    public Boolean getAligned() {
        return isAligned;
    }

    public void setAligned(Boolean algned) {
        isAligned = algned;
    }

    public AlignedCTDData getAlignedData() {
        return alignedData;
    }

    public void setAlignedData(AlignedCTDData alignedData) {
        this.alignedData = alignedData;
    }

    public ProcessedCTDHead getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedCTDHead headID) {
        HeadID = headID;
    }
}
