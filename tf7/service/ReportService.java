package com.tr.service;

import java.util.Map;

public interface ReportService {

    Map<String, Object> generateSummary();

    Map<String, Long> getCandidateStatusCount();

    Map<String, Long> getJobStatusCount();

    Map<String, Long> getPopularSkills();
}
