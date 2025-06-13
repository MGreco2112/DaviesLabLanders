package com.davies.lab.lander.FormattedModels.ResponseBody;

import com.davies.lab.lander.Models.ProcessedCTDData;

import java.time.LocalDateTime;

public class CTDDataResponse {
    private Long ID;
    private LocalDateTime Date;
    private Double TempDegC;
    private Double Sal;
    private Double CondMsCm;
    private Double Ec25UsCm;
    private Double BattV;
    private Boolean isAligned = false;
    private Long HeadID;

    public CTDDataResponse(Long ID, LocalDateTime date, Double tempDegC, Double sal, Double condMsCm, Double ec25UsCm, Double battV, Long headID) {
        this.ID = ID;
        Date = date;
        TempDegC = tempDegC;
        Sal = sal;
        CondMsCm = condMsCm;
        Ec25UsCm = ec25UsCm;
        BattV = battV;
        HeadID = headID;
    }

    public CTDDataResponse(ProcessedCTDData processedData) {
        this.ID = processedData.getID();
        Date = processedData.getDate();
        TempDegC = processedData.getTempDegC();
        Sal = processedData.getSal();
        CondMsCm = processedData.getCondMsCm();
        Ec25UsCm = processedData.getEc25UsCm();
        BattV = processedData.getBattV();
        this.isAligned = processedData.getAligned();
        HeadID = processedData.getHeadID().getHeadID();
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
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

    public void setAligned(Boolean aligned) {
        isAligned = aligned;
    }

    public Long getHeadID() {
        return HeadID;
    }

    public void setHeadID(Long headID) {
        HeadID = headID;
    }
}
