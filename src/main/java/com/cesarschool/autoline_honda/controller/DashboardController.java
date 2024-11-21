package com.cesarschool.autoline_honda.controller;

import com.cesarschool.autoline_honda.domain.DashboardSummary;
import com.cesarschool.autoline_honda.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/dashboard")
    public DashboardSummary getDashboardSummary(
            @RequestParam String startDate,
            @RequestParam String endDate) {
        return dashboardService.getDashboardSummary(startDate, endDate);
    }
}
