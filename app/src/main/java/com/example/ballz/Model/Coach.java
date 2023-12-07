package com.example.ballz.Model;

public class Coach {
    String id, nameCoach, imgCoach;

    public Coach(String id, String nameCoach, String imgCoach) {
        this.id = id;
        this.nameCoach = nameCoach;
        this.imgCoach = imgCoach;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameCoach() {
        return nameCoach;
    }

    public void setNameCoach(String nameCoach) {
        this.nameCoach = nameCoach;
    }

    public String getImgCoach() {
        return imgCoach;
    }

    public void setImgCoach(String imgCoach) {
        this.imgCoach = imgCoach;
    }

}
