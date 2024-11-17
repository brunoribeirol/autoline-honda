package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.Goals;
import com.cesarschool.autoline_honda.repository.GoalsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GoalsService {

    private final GoalsRepository goalsRepository;

    @Autowired
    public GoalsService(GoalsRepository goalsRepository) {
        this.goalsRepository = goalsRepository;
    }

    public Goals createGoal(Goals goal) {
        int rowsAffected = goalsRepository.saveGoal(goal);
        if (rowsAffected == 1) {
            return goal;
        } else {
            throw new RuntimeException("Failed to create goal");
        }
    }

    public Goals updateGoal(Goals goalId) {
        int rowsAffected = goalsRepository.updateGoal(goalId);
        if (rowsAffected == 1) {
            return goalId;
        } else {
            throw new RuntimeException("Failed to update goal with id: " + goalId.getGoalId());
        }
    }

    public void deleteGoalByGoalId(int goalId) {
        int rowsAffected = goalsRepository.deleteGoalByGoalId(goalId);
        if (rowsAffected != 1) {
            throw new RuntimeException("Failed to delete goal with id: " + goalId);
        }
    }

    public Optional<Goals> findGoalByGoalId(int goalId) {
        return goalsRepository.findGoalByGoalId(goalId);
    }

    public List<Goals> findAllGoals() {
        return goalsRepository.findAllGoals();
    }
}
