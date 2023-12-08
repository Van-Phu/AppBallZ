package com.example.ballz.Model;

public class HistoryFight {
    String nameHome, nameAway, scoreHome, scoreAway;

    public HistoryFight(String nameHome, String nameAway, String scoreHome, String scoreAway) {
        this.nameHome = nameHome;
        this.nameAway = nameAway;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
    }

    public String getNameHome() {
        return nameHome;
    }

    public void setNameHome(String nameHome) {
        this.nameHome = nameHome;
    }

    public String getNameAway() {
        return nameAway;
    }

    public void setNameAway(String nameAway) {
        this.nameAway = nameAway;
    }

    public String getScoreHome() {
        return scoreHome;
    }

    public void setScoreHome(String scoreHome) {
        this.scoreHome = scoreHome;
    }

    public String getScoreAway() {
        return scoreAway;
    }

    public void setScoreAway(String scoreAway) {
        this.scoreAway = scoreAway;
    }
}
