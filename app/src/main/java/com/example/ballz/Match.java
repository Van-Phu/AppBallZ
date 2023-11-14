package com.example.ballz;

public class Match {
    String logoAway, logoHome, time, scoreHome, scoreAway;
    public Match(String logoAway, String logoHome, String time, String scoreHome, String scoreAway) {
        this.logoAway = logoAway;
        this.logoHome = logoHome;
        this.time = time;
        this.scoreHome = scoreHome;
        this.scoreAway = scoreAway;
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
