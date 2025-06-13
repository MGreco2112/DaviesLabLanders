package com.davies.lab.lander.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class ProcessedCTDData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;
    @Column(nullable = false)
    private LocalDateTime Date;
    @Column(nullable = false)
    private Double TempDegC;
    @Column(nullable = false)
    private Double Sal;
    @Column(nullable = false)
    private Double CondMsCm;
    @Column(nullable = false)
    private Double Ec25UsCm;
    @Column(nullable = false)
    private Double BattV;
    private Boolean isAligned;
    @OneToOne(mappedBy = "rawData")
    @JsonIgnore
    private AlignedCTDData alignedData;
    @ManyToOne
    @JoinColumn(name = "Head_ID", referencedColumnName = "HeadID")
    private ProcessedCTDHead HeadID;


    public ProcessedCTDData() {
    }

    public ProcessedCTDData(LocalDateTime date, Double tempDegC, Double sal, Double condMsCm, Double ec25UsCm, Double battV, ProcessedCTDHead headID) {
        Date = date;
        TempDegC = tempDegC;
        Sal = sal;
        CondMsCm = condMsCm;
        Ec25UsCm = ec25UsCm;
        BattV = battV;
        this.isAligned = false;
        HeadID = headID;
    }

    public ProcessedCTDData(Long ID, LocalDateTime date, Double tempDegC, Double sal, Double condMsCm, Double ec25UsCm, Double battV, Boolean isAligned, AlignedCTDData alignedData, ProcessedCTDHead headID) {
        this.ID = ID;
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
