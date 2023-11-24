package com.example.ballz.Model;

public class Match {
    String logoAway, logoHome, time, scoreHome, scoreAway, idFeed;
    public Match(String logoAway, String logoHome, String time, String scoreHome, String scoreAway, String idFeed) {
        this.logoAway = logoAway;
        this.logoHome = logoHome;
        this.time = time;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
        this.idFeed = idFeed;
    }

    public String getIdFeed() {
        return idFeed;
    }

    public void setIdFeed(String idFeed) {
        this.idFeed = idFeed;
    }

    public String getLogoAway() {
        return logoAway;
    }

    public void setLogoAway(String logoAway) {
        this.logoAway = logoAway;
    }

    public String getLogoHome() {
        return logoHome;
    }

    public void setLogoHome(String logoHome) {
        this.logoHome = logoHome;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
