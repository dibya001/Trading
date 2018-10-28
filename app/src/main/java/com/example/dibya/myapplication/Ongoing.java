package com.example.dibya.myapplication;

import java.io.Serializable;

public class Ongoing implements Serializable{
    String type, switch2, warnings, checkboxinfo, date, entry, target, stop, userId, time;
    long timeStamp;
    boolean tradetaken;
    String time1,time2,time3,time4,time5,time6;

  

    public String getTime1() {
        return time1;
    }

    public void setTime1(String time1) {
        this.time1 = time1;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getTime3() {
        return time3;
    }

    public void setTime3(String time3) {
        this.time3 = time3;
    }

    public String getTime4() {
        return time4;
    }

    public void setTime4(String time4) {
        this.time4 = time4;
    }

    public String getTime5() {
        return time5;
    }

    public void setTime5(String time5) {
        this.time5 = time5;
    }

    public String getTime6() {
        return time6;
    }

    public void setTime6(String time6) {
        this.time6 = time6;
    }

    public boolean getTradetaken()
    {
        return tradetaken;
    }
    public void setTradetaken(boolean tradetaken)
    {
        this.tradetaken=tradetaken;
    }
    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUserId() {
        return userId;
    }

    public String getEntry() {
        return entry;
    }

    public String getTarget() {
        return target;
    }

    public String getSwitch2() {
        return switch2;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getCheckboxinfo() {
        return checkboxinfo;
    }

    public void setCheckboxinfo(String checkboxinfo) {
        this.checkboxinfo = checkboxinfo;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWarnings() {
        return warnings;
    }

    public void setWarnings(String warnings) {
        this.warnings = warnings;
    }

    public String getStop() {
        return stop;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSwitch2(String switch2) {
        this.switch2 = switch2;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
