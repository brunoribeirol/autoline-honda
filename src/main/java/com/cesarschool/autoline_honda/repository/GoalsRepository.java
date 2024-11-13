package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.Goals;

import java.util.List;
import java.util.Optional;

public interface GoalsRepository {
    // Save a new goal
    int saveGoal(Goals goal);

    // Update an existing goal
    int updateGoal(Goals goal);

    // Delete a goal by its idGoal
    int deleteGoalByGoalId(int goalId);

    // Find a goal by its idGoal
    Optional<Goals> findGoalByGoalId(int goalId);

    // Find all goals
    List<Goals> findAllGoals();
}
