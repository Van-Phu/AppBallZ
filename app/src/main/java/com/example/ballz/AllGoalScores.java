package com.example.ballz;

public class AllGoalScores {
    String  player_name, imgClub;
    int goal, team_name, rank;

    public AllGoalScores(String player_name, int goal, int team_name, int rank) {
        this.player_name = player_name;
        this.goal = goal;
        this.team_name = team_name;
        this.rank = rank;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getImgClub() {
        return imgClub;
    }

    public void setImgClub(String imgClub) {
        this.imgClub = imgClub;
    }

    public int getGoal() {
        return goal;
    }

    public void setGoal(int goal) {
        this.goal = goal;
    }

    public int getTeam_name() {
        return team_name;
    }

    public void setTeam_name(int team_name) {
        this.team_name = team_name;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
