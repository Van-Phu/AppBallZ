package com.example.ballz.Model;

public class Matchup {
    String date, logoHome, logoAway, tour, score;

    public Matchup(String date, String logoHome, String logoAway, String tour, String score) {
        this.date = date;
        this.logoHome = logoHome;
        this.logoAway = logoAway;
        this.tour = tour;
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLogoHome() {
        return logoHome;
    }

    public void setLogoHome(String logoHome) {
        this.logoHome = logoHome;
    }

    public String getLogoAway() {
        return logoAway;
    }

    public void setLogoAway(String logoAway) {
        this.logoAway = logoAway;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
