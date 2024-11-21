package com.cesarschool.autoline_honda.service;

import com.cesarschool.autoline_honda.domain.DashboardSummary;
import com.cesarschool.autoline_honda.repository.DashboardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    private final DashboardRepository dashboardRepository;

    @Autowired
    public DashboardService(DashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public DashboardSummary getDashboardSummary(String startDate, String endDate) {
        return dashboardRepository.getDashboardSummary(startDate, endDate);
    }
}
