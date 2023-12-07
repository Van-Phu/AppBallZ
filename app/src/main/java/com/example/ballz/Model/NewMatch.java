package com.example.ballz.Model;

public class NewMatch {
    String id, eventStart, nameHome, nameAway, logoHome, logoAway;

    public NewMatch(String id, String eventStart, String nameHome, String nameAway, String logoHome, String logoAway) {
        this.id = id;
        this.eventStart = eventStart;
        this.nameHome = nameHome;
        this.nameAway = nameAway;
        this.logoHome = logoHome;
        this.logoAway = logoAway;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventStart() {
        return eventStart;
    }

    public void setEventStart(String eventStart) {
        this.eventStart = eventStart;
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
}
