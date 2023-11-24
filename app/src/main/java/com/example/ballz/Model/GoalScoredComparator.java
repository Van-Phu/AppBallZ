package com.example.ballz.Model;

import java.util.Comparator;

public class GoalScoredComparator implements Comparator<GoalScored> {
    @Override
    public int compare(GoalScored o1, GoalScored o2) {
        return Integer.compare(o2.getGoals(), o1.getGoals());
    }
}
