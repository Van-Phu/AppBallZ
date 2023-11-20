package com.example.ballz;

import java.util.Comparator;

public class TopScoresComparator implements Comparator<TopScores> {
    @Override
    public int compare(TopScores o1, TopScores o2) {
        return Integer.compare(o2.getGoals(), o1.getGoals());
    }
}
