package com.example.dibya.myapplication;

import java.io.Serializable;

public class Ongoing implements Serializable{
    String type, switch2, warnings, checkboxinfo, date, entry, target, stop, userId, time;
    long timeStamp;
    boolean tradetaken;


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
