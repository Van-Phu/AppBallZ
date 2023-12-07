package com.example.ballz.Model;

public class Player {
    String idPlayer, namePlayer, countryName;

    public Player(String idPlayer, String namePlayer, String countryName) {
        this.idPlayer = idPlayer;
        this.namePlayer = namePlayer;
        this.countryName = countryName;
    }

    public String getIdPlayer() {
        return idPlayer;
    }

    public void setIdPlayer(String idPlayer) {
        this.idPlayer = idPlayer;
    }

    public String getNamePlayer() {
        return namePlayer;
    }

    public void setNamePlayer(String namePlayer) {
        this.namePlayer = namePlayer;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
}
