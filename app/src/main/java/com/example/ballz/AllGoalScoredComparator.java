package com.example.ballz;

import java.util.Comparator;

public class AllGoalScoredComparator implements Comparator<AllGoalScores> {
    @Override
    public int compare(AllGoalScores o1, AllGoalScores o2) {
        return Integer.compare(o2.getGoal(), o1.getGoal());
    }
}
