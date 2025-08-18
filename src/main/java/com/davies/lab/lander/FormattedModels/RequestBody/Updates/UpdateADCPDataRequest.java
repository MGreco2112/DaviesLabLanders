package com.davies.lab.lander.FormattedModels.RequestBody.Updates;

import com.davies.lab.lander.Models.Data.Aligned.AlignedADCPData;
import com.davies.lab.lander.Models.Headers.ProcessedADCPHead;

import java.time.LocalDateTime;

public class UpdateADCPDataRequest {
    private LocalDateTime Date;
    private Double Battery;
    private Double Heading;
    private Double Pitch;
    private Double Roll;
    private Double Pressure;
    private Double Temperature;
    private Double AnalogIn1;
    private Double AnalogIn2;
    private Double Speed1_1_0m;
    private Double Speed2_1_5m;
    private Double Speed3_2_0m;
    private Double Speed4_2_5m;
    private Double Speed5_3_0m;
    private Double Speed6_3_5m;
    private Double Speed7_4_0m;
    private Double Speed8_4_5m;
    private Double Speed9_5_0m;
    private Double Speed10_5_5m;
    private Double Speed11_6_0m;
    private Double Speed12_6_5m;
    private Double Speed13_7_0m;
    private Double Speed14_7_5m;
    private Double Speed15_8_0m;
    private Double Speed16_8_5m;
    private Double Speed17_9_0m;
    private Double Speed18_9_5m;
    private Double Speed19_10_0m;
    private Double Speed20_10_5m;
    private Double Dir1_1_0m;
    private Double Dir2_1_5m;
    private Double Dir3_2_0m;
    private Double Dir4_2_5m;
    private Double Dir5_3_0m;
    private Double Dir6_3_5m;
    private Double Dir7_4_0m;
    private Double Dir8_4_5m;
    private Double Dir9_5_0m;
    private Double Dir10_5_5m;
    private Double Dir11_6_0m;
    private Double Dir12_6_5m;
    private Double Dir13_7_0m;
    private Double Dir14_7_5m;
    private Double Dir15_8_0m;
    private Double Dir16_8_5m;
    private Double Dir17_9_0m;
    private Double Dir18_9_5m;
    private Double Dir19_10_0m;
    private Double Dir20_10_5m;
    private Boolean isAligned;
    private AlignedADCPData alignedData;
    private ProcessedADCPHead HeadID;

    public UpdateADCPDataRequest() {
    }

    public UpdateADCPDataRequest(LocalDateTime date, Double battery, Double heading, Double pitch, Double roll, Double pressure, Double temperature, Double analogIn1, Double analogIn2, Double speed1_1_0m, Double speed2_1_5m, Double speed3_2_0m, Double speed4_2_5m, Double speed5_3_0m, Double speed6_3_5m, Double speed7_4_0m, Double speed8_4_5m, Double speed9_5_0m, Double speed10_5_5m, Double speed11_6_0m, Double speed12_6_5m, Double speed13_7_0m, Double speed14_7_5m, Double speed15_8_0m, Double speed16_8_5m, Double speed17_9_0m, Double speed18_9_5m, Double speed19_10_0m, Double speed20_10_5m, Double dir1_1_0m, Double dir2_1_5m, Double dir3_2_0m, Double dir4_2_5m, Double dir5_3_0m, Double dir6_3_5m, Double dir7_4_0m, Double dir8_4_5m, Double dir9_5_0m, Double dir10_5_5m, Double dir11_6_0m, Double dir12_6_5m, Double dir13_7_0m, Double dir14_7_5m, Double dir15_8_0m, Double dir16_8_5m, Double dir17_9_0m, Double dir18_9_5m, Double dir19_10_0m, Double dir20_10_5m, Boolean isAligned, AlignedADCPData alignedData, ProcessedADCPHead headID) {
        Date = date;
        Battery = battery;
        Heading = heading;
        Pitch = pitch;
        Roll = roll;
        Pressure = pressure;
        Temperature = temperature;
        AnalogIn1 = analogIn1;
        AnalogIn2 = analogIn2;
        Speed1_1_0m = speed1_1_0m;
        Speed2_1_5m = speed2_1_5m;
        Speed3_2_0m = speed3_2_0m;
        Speed4_2_5m = speed4_2_5m;
        Speed5_3_0m = speed5_3_0m;
        Speed6_3_5m = speed6_3_5m;
        Speed7_4_0m = speed7_4_0m;
        Speed8_4_5m = speed8_4_5m;
        Speed9_5_0m = speed9_5_0m;
        Speed10_5_5m = speed10_5_5m;
        Speed11_6_0m = speed11_6_0m;
        Speed12_6_5m = speed12_6_5m;
        Speed13_7_0m = speed13_7_0m;
        Speed14_7_5m = speed14_7_5m;
        Speed15_8_0m = speed15_8_0m;
        Speed16_8_5m = speed16_8_5m;
        Speed17_9_0m = speed17_9_0m;
        Speed18_9_5m = speed18_9_5m;
        Speed19_10_0m = speed19_10_0m;
        Speed20_10_5m = speed20_10_5m;
        Dir1_1_0m = dir1_1_0m;
        Dir2_1_5m = dir2_1_5m;
        Dir3_2_0m = dir3_2_0m;
        Dir4_2_5m = dir4_2_5m;
        Dir5_3_0m = dir5_3_0m;
        Dir6_3_5m = dir6_3_5m;
        Dir7_4_0m = dir7_4_0m;
        Dir8_4_5m = dir8_4_5m;
        Dir9_5_0m = dir9_5_0m;
        Dir10_5_5m = dir10_5_5m;
        Dir11_6_0m = dir11_6_0m;
        Dir12_6_5m = dir12_6_5m;
        Dir13_7_0m = dir13_7_0m;
        Dir14_7_5m = dir14_7_5m;
        Dir15_8_0m = dir15_8_0m;
        Dir16_8_5m = dir16_8_5m;
        Dir17_9_0m = dir17_9_0m;
        Dir18_9_5m = dir18_9_5m;
        Dir19_10_0m = dir19_10_0m;
        Dir20_10_5m = dir20_10_5m;
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

    public Double getBattery() {
        return Battery;
    }

    public void setBattery(Double battery) {
        Battery = battery;
    }

    public Double getHeading() {
        return Heading;
    }

    public void setHeading(Double heading) {
        Heading = heading;
    }

    public Double getPitch() {
        return Pitch;
    }

    public void setPitch(Double pitch) {
        Pitch = pitch;
    }

    public Double getRoll() {
        return Roll;
    }

    public void setRoll(Double roll) {
        Roll = roll;
    }

    public Double getPressure() {
        return Pressure;
    }

    public void setPressure(Double pressure) {
        Pressure = pressure;
    }

    public Double getTemperature() {
        return Temperature;
    }

    public void setTemperature(Double temperature) {
        Temperature = temperature;
    }

    public Double getAnalogIn1() {
        return AnalogIn1;
    }

    public void setAnalogIn1(Double analogIn1) {
        AnalogIn1 = analogIn1;
    }

    public Double getAnalogIn2() {
        return AnalogIn2;
    }

    public void setAnalogIn2(Double analogIn2) {
        AnalogIn2 = analogIn2;
    }

    public Double getSpeed1_1_0m() {
        return Speed1_1_0m;
    }

    public void setSpeed1_1_0m(Double speed1_1_0m) {
        Speed1_1_0m = speed1_1_0m;
    }

    public Double getSpeed2_1_5m() {
        return Speed2_1_5m;
    }

    public void setSpeed2_1_5m(Double speed2_1_5m) {
        Speed2_1_5m = speed2_1_5m;
    }

    public Double getSpeed3_2_0m() {
        return Speed3_2_0m;
    }

    public void setSpeed3_2_0m(Double speed3_2_0m) {
        Speed3_2_0m = speed3_2_0m;
    }

    public Double getSpeed4_2_5m() {
        return Speed4_2_5m;
    }

    public void setSpeed4_2_5m(Double speed4_2_5m) {
        Speed4_2_5m = speed4_2_5m;
    }

    public Double getSpeed5_3_0m() {
        return Speed5_3_0m;
    }

    public void setSpeed5_3_0m(Double speed5_3_0m) {
        Speed5_3_0m = speed5_3_0m;
    }

    public Double getSpeed6_3_5m() {
        return Speed6_3_5m;
    }

    public void setSpeed6_3_5m(Double speed6_3_5m) {
        Speed6_3_5m = speed6_3_5m;
    }

    public Double getSpeed7_4_0m() {
        return Speed7_4_0m;
    }

    public void setSpeed7_4_0m(Double speed7_4_0m) {
        Speed7_4_0m = speed7_4_0m;
    }

    public Double getSpeed8_4_5m() {
        return Speed8_4_5m;
    }

    public void setSpeed8_4_5m(Double speed8_4_5m) {
        Speed8_4_5m = speed8_4_5m;
    }

    public Double getSpeed9_5_0m() {
        return Speed9_5_0m;
    }

    public void setSpeed9_5_0m(Double speed9_5_0m) {
        Speed9_5_0m = speed9_5_0m;
    }

    public Double getSpeed10_5_5m() {
        return Speed10_5_5m;
    }

    public void setSpeed10_5_5m(Double speed10_5_5m) {
        Speed10_5_5m = speed10_5_5m;
    }

    public Double getSpeed11_6_0m() {
        return Speed11_6_0m;
    }

    public void setSpeed11_6_0m(Double speed11_6_0m) {
        Speed11_6_0m = speed11_6_0m;
    }

    public Double getSpeed12_6_5m() {
        return Speed12_6_5m;
    }

    public void setSpeed12_6_5m(Double speed12_6_5m) {
        Speed12_6_5m = speed12_6_5m;
    }

    public Double getSpeed13_7_0m() {
        return Speed13_7_0m;
    }

    public void setSpeed13_7_0m(Double speed13_7_0m) {
        Speed13_7_0m = speed13_7_0m;
    }

    public Double getSpeed14_7_5m() {
        return Speed14_7_5m;
    }

    public void setSpeed14_7_5m(Double speed14_7_5m) {
        Speed14_7_5m = speed14_7_5m;
    }

    public Double getSpeed15_8_0m() {
        return Speed15_8_0m;
    }

    public void setSpeed15_8_0m(Double speed15_8_0m) {
        Speed15_8_0m = speed15_8_0m;
    }

    public Double getSpeed16_8_5m() {
        return Speed16_8_5m;
    }

    public void setSpeed16_8_5m(Double speed16_8_5m) {
        Speed16_8_5m = speed16_8_5m;
    }

    public Double getSpeed17_9_0m() {
        return Speed17_9_0m;
    }

    public void setSpeed17_9_0m(Double speed17_9_0m) {
        Speed17_9_0m = speed17_9_0m;
    }

    public Double getSpeed18_9_5m() {
        return Speed18_9_5m;
    }

    public void setSpeed18_9_5m(Double speed18_9_5m) {
        Speed18_9_5m = speed18_9_5m;
    }

    public Double getSpeed19_10_0m() {
        return Speed19_10_0m;
    }

    public void setSpeed19_10_0m(Double speed19_10_0m) {
        Speed19_10_0m = speed19_10_0m;
    }

    public Double getSpeed20_10_5m() {
        return Speed20_10_5m;
    }

    public void setSpeed20_10_5m(Double speed20_10_5m) {
        Speed20_10_5m = speed20_10_5m;
    }

    public Double getDir1_1_0m() {
        return Dir1_1_0m;
    }

    public void setDir1_1_0m(Double dir1_1_0m) {
        Dir1_1_0m = dir1_1_0m;
    }

    public Double getDir2_1_5m() {
        return Dir2_1_5m;
    }

    public void setDir2_1_5m(Double dir2_1_5m) {
        Dir2_1_5m = dir2_1_5m;
    }

    public Double getDir3_2_0m() {
        return Dir3_2_0m;
    }

    public void setDir3_2_0m(Double dir3_2_0m) {
        Dir3_2_0m = dir3_2_0m;
    }

    public Double getDir4_2_5m() {
        return Dir4_2_5m;
    }

    public void setDir4_2_5m(Double dir4_2_5m) {
        Dir4_2_5m = dir4_2_5m;
    }

    public Double getDir5_3_0m() {
        return Dir5_3_0m;
    }

    public void setDir5_3_0m(Double dir5_3_0m) {
        Dir5_3_0m = dir5_3_0m;
    }

    public Double getDir6_3_5m() {
        return Dir6_3_5m;
    }

    public void setDir6_3_5m(Double dir6_3_5m) {
        Dir6_3_5m = dir6_3_5m;
    }

    public Double getDir7_4_0m() {
        return Dir7_4_0m;
    }

    public void setDir7_4_0m(Double dir7_4_0m) {
        Dir7_4_0m = dir7_4_0m;
    }

    public Double getDir8_4_5m() {
        return Dir8_4_5m;
    }

    public void setDir8_4_5m(Double dir8_4_5m) {
        Dir8_4_5m = dir8_4_5m;
    }

    public Double getDir9_5_0m() {
        return Dir9_5_0m;
    }

    public void setDir9_5_0m(Double dir9_5_0m) {
        Dir9_5_0m = dir9_5_0m;
    }

    public Double getDir10_5_5m() {
        return Dir10_5_5m;
    }

    public void setDir10_5_5m(Double dir10_5_5m) {
        Dir10_5_5m = dir10_5_5m;
    }

    public Double getDir11_6_0m() {
        return Dir11_6_0m;
    }

    public void setDir11_6_0m(Double dir11_6_0m) {
        Dir11_6_0m = dir11_6_0m;
    }

    public Double getDir12_6_5m() {
        return Dir12_6_5m;
    }

    public void setDir12_6_5m(Double dir12_6_5m) {
        Dir12_6_5m = dir12_6_5m;
    }

    public Double getDir13_7_0m() {
        return Dir13_7_0m;
    }

    public void setDir13_7_0m(Double dir13_7_0m) {
        Dir13_7_0m = dir13_7_0m;
    }

    public Double getDir14_7_5m() {
        return Dir14_7_5m;
    }

    public void setDir14_7_5m(Double dir14_7_5m) {
        Dir14_7_5m = dir14_7_5m;
    }

    public Double getDir15_8_0m() {
        return Dir15_8_0m;
    }

    public void setDir15_8_0m(Double dir15_8_0m) {
        Dir15_8_0m = dir15_8_0m;
    }

    public Double getDir16_8_5m() {
        return Dir16_8_5m;
    }

    public void setDir16_8_5m(Double dir16_8_5m) {
        Dir16_8_5m = dir16_8_5m;
    }

    public Double getDir17_9_0m() {
        return Dir17_9_0m;
    }

    public void setDir17_9_0m(Double dir17_9_0m) {
        Dir17_9_0m = dir17_9_0m;
    }

    public Double getDir18_9_5m() {
        return Dir18_9_5m;
    }

    public void setDir18_9_5m(Double dir18_9_5m) {
        Dir18_9_5m = dir18_9_5m;
    }

    public Double getDir19_10_0m() {
        return Dir19_10_0m;
    }

    public void setDir19_10_0m(Double dir19_10_0m) {
        Dir19_10_0m = dir19_10_0m;
    }

    public Double getDir20_10_5m() {
        return Dir20_10_5m;
    }

    public void setDir20_10_5m(Double dir20_10_5m) {
        Dir20_10_5m = dir20_10_5m;
    }

    public Boolean getAligned() {
        return isAligned;
    }

    public void setAligned(Boolean aligned) {
        isAligned = aligned;
    }

    public AlignedADCPData getAlignedData() {
        return alignedData;
    }

    public void setAlignedData(AlignedADCPData alignedData) {
        this.alignedData = alignedData;
    }

    public ProcessedADCPHead getHeadID() {
        return HeadID;
    }

    public void setHeadID(ProcessedADCPHead headID) {
        HeadID = headID;
    }
}
