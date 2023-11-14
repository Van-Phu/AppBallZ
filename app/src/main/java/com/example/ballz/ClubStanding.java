package com.example.ballz;

public class ClubStanding {
    String img;
    String nameClub;
    String winNumb;
    String drawNumb;
    String loseNumb;
    String point;

    public ClubStanding(String img, String nameClub, String winNumb, String drawNumb, String loseNumb, String point) {
        this.img = img;
        this.nameClub = nameClub;
        this.winNumb = winNumb;
        this.drawNumb = drawNumb;
        this.loseNumb = loseNumb;
        this.point = point;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNameClub() {
        return nameClub;
    }

    public void setNameClub(String nameClub) {
        this.nameClub = nameClub;
    }

    public String getWinNumb() {
        return winNumb;
    }

    public void setWinNumb(String winNumb) {
        this.winNumb = winNumb;
    }

    public String getDrawNumb() {
        return drawNumb;
    }

    public void setDrawNumb(String drawNumb) {
        this.drawNumb = drawNumb;
    }

    public String getLoseNumb() {
        return loseNumb;
    }

    public void setLoseNumb(String loseNumb) {
        this.loseNumb = loseNumb;
    }

    public String getPoint() {
        return point;
    }

    public void setPoint(String point) {
        this.point = point;
    }
}
