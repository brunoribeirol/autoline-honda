package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.Goals;
import com.cesarschool.autoline_honda.service.GoalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/goals")
public class GoalsController {

    private final GoalsService goalsService;

    @Autowired
    public GoalsController(GoalsService goalsService) {
        this.goalsService = goalsService;
    }

    @PostMapping
    public ResponseEntity<Goals> createGoal(@RequestBody Goals goal) {
        try {
            Goals createdGoal = goalsService.createGoal(goal);
            return new ResponseEntity<>(createdGoal, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{goalId}")
    public ResponseEntity<Goals> getGoalByGoalId(@PathVariable int goalId) {
        Optional<Goals> goalOptional = goalsService.findGoalByGoalId(goalId);
        return goalOptional.map(goal -> new ResponseEntity<>(goal, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Goals>> getAllGoals() {
        List<Goals> goals = goalsService.findAllGoals();
        return new ResponseEntity<>(goals, HttpStatus.OK);
    }

    @PutMapping("/{goalId}")
    public ResponseEntity<Goals> updateGoal(@PathVariable int goalId, @RequestBody Goals goal) {
        goal.setGoalId(goalId);
        try {
            Goals updatedGoal = goalsService.updateGoal(goal);
            return new ResponseEntity<>(updatedGoal, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{goalId}")
    public ResponseEntity<Void> deleteGoal(@PathVariable int goalId) {
        try {
            goalsService.deleteGoalByGoalId(goalId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
