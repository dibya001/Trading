package com.example.dibya.myapplication;

public class TradeData {
    int profit;
    String type, switch2, comments, warnings, checkboxinfo, date, entry, target, protype, stop, userId;

    public String getUserId() {
        return userId;
    }

    public String getEntry() {
        return entry;
    }

    public String getProtype() {
        return protype;
    }

    public String getTarget() {
        return target;
    }

    public int getProfit() {
        return profit;
    }

    public String getComments() {
        return comments;
    }

    public String getSwitch2() {
        return switch2;
    }

    public void setProfit(int profit) {
        this.profit = profit;
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

    public void setProtype(String protype) {
        this.protype = protype;
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

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }
}
