package com.example.ballz;

public class TopScores {
    String  player_name, team_name;
    int goals;

    public TopScores(int goals, String player_name) {
        this.goals = goals;
        this.player_name = player_name;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public String getPlayer_name() {
        return player_name;
    }

    public void setPlayer_name(String player_name) {
        this.player_name = player_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

}
