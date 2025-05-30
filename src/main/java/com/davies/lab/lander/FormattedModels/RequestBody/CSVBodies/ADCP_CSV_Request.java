package com.davies.lab.lander.FormattedModels.RequestBody.CSVBodies;

import com.opencsv.bean.CsvBindByName;

public class ADCP_CSV_Request {
    @CsvBindByName(column = "DateTime")
    private String date;
    @CsvBindByName(column = "Battery")
    private Double battery;
    @CsvBindByName(column = "Heading")
    private Double heading;
    @CsvBindByName(column = "Pitch")
    private Double pitch;
    @CsvBindByName(column = "Roll")
    private Double roll;
    @CsvBindByName(column = "Pressure")
    private Double pressure;
    @CsvBindByName(column = "Temperature")
    private Double temperature;
    @CsvBindByName(column = "AnalogIn1")
    private Double analogIn1;
    @CsvBindByName(column = "AnalogIn2")
    private Double analogIn2;
    @CsvBindByName(column = "Speed#1(1.0m)")
    private Double speed1_1_0m;
    @CsvBindByName(column = "Dir#1(1.0m)")
    private Double dir1_1_0m;
    @CsvBindByName(column = "Speed#2(1.5m)")
    private Double speed2_1_5m;
    @CsvBindByName(column = "Dir#2(1.5m)")
    private Double dir2_1_5m;
    @CsvBindByName(column = "Speed#3(2.0m)")
    private Double speed3_2_0m;
    @CsvBindByName(column = "Dir#3(2.0m)")
    private Double dir3_2_0m;
    @CsvBindByName(column = "Speed#4(2.5m)")
    private Double speed4_2_5m;
    @CsvBindByName(column = "Dir#4(2.5m)")
    private Double dir4_2_5m;
    @CsvBindByName(column = "Speed#5(3.0m)")
    private Double speed5_3_0m;
    @CsvBindByName(column = "Dir#5(3.0m)")
    private Double dir5_3_0m;
    @CsvBindByName(column = "Speed#6(3.5m)")
    private Double speed6_3_5m;
    @CsvBindByName(column = "Dir#6(3.5m)")
    private Double dir6_3_5m;
    @CsvBindByName(column = "Speed#7(4.0m)")
    private Double speed7_4_0m;
    @CsvBindByName(column = "Dir#7(4.0m)")
    private Double dir7_4_0m;
    @CsvBindByName(column = "Speed#8(4.5m)")
    private Double speed8_4_5m;
    @CsvBindByName(column = "Dir#8(4.5m)")
    private Double dir8_4_5m;
    @CsvBindByName(column = "Speed#9(5.0m)")
    private Double speed9_5_0m;
    @CsvBindByName(column = "Dir#9(5.0m)")
    private Double dir9_5_0m;
    @CsvBindByName(column = "Speed#10(5.5m)")
    private Double speed10_5_5m;
    @CsvBindByName(column = "Dir#10(5.5m)")
    private Double dir10_5_5m;
    @CsvBindByName(column = "Speed#11(6.0m)")
    private Double speed11_6_0m;
    @CsvBindByName(column = "Dir#11(6.0m)")
    private Double dir11_6_0m;
    @CsvBindByName(column = "Speed#12(6.5m)")
    private Double speed12_6_5m;
    @CsvBindByName(column = "Dir#12(6.5m)")
    private Double dir12_6_5m;
    @CsvBindByName(column = "Speed#13(7.0m)")
    private Double speed13_7_0m;
    @CsvBindByName(column = "Dir#13(7.0m)")
    private Double dir13_7_0m;
    @CsvBindByName(column = "Speed#14(7.5m)")
    private Double speed14_7_5m;
    @CsvBindByName(column = "Dir#14(7.5m)")
    private Double dir14_7_5m;
    @CsvBindByName(column = "Speed#15(8.0m)")
    private Double speed15_8_0m;
    @CsvBindByName(column = "Dir#15(8.0m)")
    private Double dir15_8_0m;
    @CsvBindByName(column = "Speed#16(8.5m)")
    private Double speed16_8_5m;
    @CsvBindByName(column = "Dir#16(8.5m)")
    private Double dir16_8_5m;
    @CsvBindByName(column = "Speed#17(9.0m)")
    private Double speed17_9_0m;
    @CsvBindByName(column = "Dir#17(9.0m)")
    private Double dir17_9_0m;
    @CsvBindByName(column = "Speed#18(9.5m)")
    private Double speed18_9_5m;
    @CsvBindByName(column = "Dir#18(9.5m)")
    private Double dir18_9_5m;
    @CsvBindByName(column = "Speed#19(10.0m)")
    private Double speed19_10_0m;
    @CsvBindByName(column = "Dir#19(10.0m)")
    private Double dir19_10_0m;
    @CsvBindByName(column = "Speed#20(10.5m)")
    private Double speed20_10_5m;
    @CsvBindByName(column = "Dir#20(10.5m)")
    private Double dir20_10_5m;

    public ADCP_CSV_Request() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getBattery() {
        return battery;
    }

    public void setBattery(Double battery) {
        this.battery = battery;
    }

    public Double getHeading() {
        return heading;
    }

    public void setHeading(Double heading) {
        this.heading = heading;
    }

    public Double getPitch() {
        return pitch;
    }

    public void setPitch(Double pitch) {
        this.pitch = pitch;
    }

    public Double getRoll() {
        return roll;
    }

    public void setRoll(Double roll) {
        this.roll = roll;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getAnalogIn1() {
        return analogIn1;
    }

    public void setAnalogIn1(Double analogIn1) {
        this.analogIn1 = analogIn1;
    }

    public Double getAnalogIn2() {
        return analogIn2;
    }

    public void setAnalogIn2(Double analogIn2) {
        this.analogIn2 = analogIn2;
    }

    public Double getSpeed1_1_0m() {
        return speed1_1_0m;
    }

    public void setSpeed1_1_0m(Double speed1_1_0m) {
        this.speed1_1_0m = speed1_1_0m;
    }

    public Double getDir1_1_0m() {
        return dir1_1_0m;
    }

    public void setDir1_1_0m(Double dir1_1_0m) {
        this.dir1_1_0m = dir1_1_0m;
    }

    public Double getSpeed2_1_5m() {
        return speed2_1_5m;
    }

    public void setSpeed2_1_5m(Double speed2_1_5m) {
        this.speed2_1_5m = speed2_1_5m;
    }

    public Double getDir2_1_5m() {
        return dir2_1_5m;
    }

    public void setDir2_1_5m(Double dir2_1_5m) {
        this.dir2_1_5m = dir2_1_5m;
    }

    public Double getSpeed3_2_0m() {
        return speed3_2_0m;
    }

    public void setSpeed3_2_0m(Double speed3_2_0m) {
        this.speed3_2_0m = speed3_2_0m;
    }

    public Double getDir3_2_0m() {
        return dir3_2_0m;
    }

    public void setDir3_2_0m(Double dir3_2_0m) {
        this.dir3_2_0m = dir3_2_0m;
    }

    public Double getSpeed4_2_5m() {
        return speed4_2_5m;
    }

    public void setSpeed4_2_5m(Double speed4_2_5m) {
        this.speed4_2_5m = speed4_2_5m;
    }

    public Double getDir4_2_5m() {
        return dir4_2_5m;
    }

    public void setDir4_2_5m(Double dir4_2_5m) {
        this.dir4_2_5m = dir4_2_5m;
    }

    public Double getSpeed5_3_0m() {
        return speed5_3_0m;
    }

    public void setSpeed5_3_0m(Double speed5_3_0m) {
        this.speed5_3_0m = speed5_3_0m;
    }

    public Double getDir5_3_0m() {
        return dir5_3_0m;
    }

    public void setDir5_3_0m(Double dir5_3_0m) {
        this.dir5_3_0m = dir5_3_0m;
    }

    public Double getSpeed6_3_5m() {
        return speed6_3_5m;
    }

    public void setSpeed6_3_5m(Double speed6_3_5m) {
        this.speed6_3_5m = speed6_3_5m;
    }

    public Double getDir6_3_5m() {
        return dir6_3_5m;
    }

    public void setDir6_3_5m(Double dir6_3_5m) {
        this.dir6_3_5m = dir6_3_5m;
    }

    public Double getSpeed7_4_0m() {
        return speed7_4_0m;
    }

    public void setSpeed7_4_0m(Double speed7_4_0m) {
        this.speed7_4_0m = speed7_4_0m;
    }

    public Double getDir7_4_0m() {
        return dir7_4_0m;
    }

    public void setDir7_4_0m(Double dir7_4_0m) {
        this.dir7_4_0m = dir7_4_0m;
    }

    public Double getSpeed8_4_5m() {
        return speed8_4_5m;
    }

    public void setSpeed8_4_5m(Double speed8_4_5m) {
        this.speed8_4_5m = speed8_4_5m;
    }

    public Double getDir8_4_5m() {
        return dir8_4_5m;
    }

    public void setDir8_4_5m(Double dir8_4_5m) {
        this.dir8_4_5m = dir8_4_5m;
    }

    public Double getSpeed9_5_0m() {
        return speed9_5_0m;
    }

    public void setSpeed9_5_0m(Double speed9_5_0m) {
        this.speed9_5_0m = speed9_5_0m;
    }

    public Double getDir9_5_0m() {
        return dir9_5_0m;
    }

    public void setDir9_5_0m(Double dir9_5_0m) {
        this.dir9_5_0m = dir9_5_0m;
    }

    public Double getSpeed10_5_5m() {
        return speed10_5_5m;
    }

    public void setSpeed10_5_5m(Double speed10_5_5m) {
        this.speed10_5_5m = speed10_5_5m;
    }

    public Double getDir10_5_5m() {
        return dir10_5_5m;
    }

    public void setDir10_5_5m(Double dir10_5_5m) {
        this.dir10_5_5m = dir10_5_5m;
    }

    public Double getSpeed11_6_0m() {
        return speed11_6_0m;
    }

    public void setSpeed11_6_0m(Double speed11_6_0m) {
        this.speed11_6_0m = speed11_6_0m;
    }

    public Double getDir11_6_0m() {
        return dir11_6_0m;
    }

    public void setDir11_6_0m(Double dir11_6_0m) {
        this.dir11_6_0m = dir11_6_0m;
    }

    public Double getSpeed12_6_5m() {
        return speed12_6_5m;
    }

    public void setSpeed12_6_5m(Double speed12_6_5m) {
        this.speed12_6_5m = speed12_6_5m;
    }

    public Double getDir12_6_5m() {
        return dir12_6_5m;
    }

    public void setDir12_6_5m(Double dir12_6_5m) {
        this.dir12_6_5m = dir12_6_5m;
    }

    public Double getSpeed13_7_0m() {
        return speed13_7_0m;
    }

    public void setSpeed13_7_0m(Double speed13_7_0m) {
        this.speed13_7_0m = speed13_7_0m;
    }

    public Double getDir13_7_0m() {
        return dir13_7_0m;
    }

    public void setDir13_7_0m(Double dir13_7_0m) {
        this.dir13_7_0m = dir13_7_0m;
    }

    public Double getSpeed14_7_5m() {
        return speed14_7_5m;
    }

    public void setSpeed14_7_5m(Double speed14_7_5m) {
        this.speed14_7_5m = speed14_7_5m;
    }

    public Double getDir14_7_5m() {
        return dir14_7_5m;
    }

    public void setDir14_7_5m(Double dir14_7_5m) {
        this.dir14_7_5m = dir14_7_5m;
    }

    public Double getSpeed15_8_0m() {
        return speed15_8_0m;
    }

    public void setSpeed15_8_0m(Double speed15_8_0m) {
        this.speed15_8_0m = speed15_8_0m;
    }

    public Double getDir15_8_0m() {
        return dir15_8_0m;
    }

    public void setDir15_8_0m(Double dir15_8_0m) {
        this.dir15_8_0m = dir15_8_0m;
    }

    public Double getSpeed16_8_5m() {
        return speed16_8_5m;
    }

    public void setSpeed16_8_5m(Double speed16_8_5m) {
        this.speed16_8_5m = speed16_8_5m;
    }

    public Double getDir16_8_5m() {
        return dir16_8_5m;
    }

    public void setDir16_8_5m(Double dir16_8_5m) {
        this.dir16_8_5m = dir16_8_5m;
    }

    public Double getSpeed17_9_0m() {
        return speed17_9_0m;
    }

    public void setSpeed17_9_0m(Double speed17_9_0m) {
        this.speed17_9_0m = speed17_9_0m;
    }

    public Double getDir17_9_0m() {
        return dir17_9_0m;
    }

    public void setDir17_9_0m(Double dir17_9_0m) {
        this.dir17_9_0m = dir17_9_0m;
    }

    public Double getSpeed18_9_5m() {
        return speed18_9_5m;
    }

    public void setSpeed18_9_5m(Double speed18_9_5m) {
        this.speed18_9_5m = speed18_9_5m;
    }

    public Double getDir18_9_5m() {
        return dir18_9_5m;
    }

    public void setDir18_9_5m(Double dir18_9_5m) {
        this.dir18_9_5m = dir18_9_5m;
    }

    public Double getSpeed19_10_0m() {
        return speed19_10_0m;
    }

    public void setSpeed19_10_0m(Double speed19_10_0m) {
        this.speed19_10_0m = speed19_10_0m;
    }

    public Double getDir19_10_0m() {
        return dir19_10_0m;
    }

    public void setDir19_10_0m(Double dir19_10_0m) {
        this.dir19_10_0m = dir19_10_0m;
    }

    public Double getSpeed20_10_5m() {
        return speed20_10_5m;
    }

    public void setSpeed20_10_5m(Double speed20_10_5m) {
        this.speed20_10_5m = speed20_10_5m;
    }

    public Double getDir20_10_5m() {
        return dir20_10_5m;
    }

    public void setDir20_10_5m(Double dir20_10_5m) {
        this.dir20_10_5m = dir20_10_5m;
    }
}
