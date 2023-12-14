package com.example.ballz.Model;

public class TimeLine {
    String HomeName;
    String AwayName;
    String Time;
    String side;

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public TimeLine(String homeName, String awayName, String time, String side) {
        this.HomeName = homeName;
        this.AwayName = awayName;
        this.side = side;
        this.Time = String.valueOf(time);
    }

    public String getHomeName() {
        return HomeName;
    }

    public void setHomeName(String homeName) {
        HomeName = homeName;
    }

    public String getAwayName() {
        return AwayName;
    }

    public void setAwayName(String awayName) {
        AwayName = awayName;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }


}
