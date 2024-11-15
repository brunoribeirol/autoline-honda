package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Goals;

import java.util.List;
import java.util.Optional;

public interface GoalsRepository {
    int saveGoal(Goals goal);
    int updateGoal(Goals goal);
    int deleteGoalByGoalId(int goalId);
    Optional<Goals> findGoalByGoalId(int goalId);
    List<Goals> findAllGoals();
}
