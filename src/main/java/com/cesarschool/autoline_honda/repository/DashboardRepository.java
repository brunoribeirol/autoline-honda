package com.cesarschool.autoline_honda.repository;

import com.cesarschool.autoline_honda.domain.DashboardSummary;

public interface DashboardRepository {
    DashboardSummary getDashboardSummary(String startDate, String endDate);
}
