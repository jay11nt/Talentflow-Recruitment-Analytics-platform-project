package com.tr.service;

public interface ScheduledService {

    void sendDailyInterviewReminders();

    void cleanUpClosedJobsOlderThan30Days();

    void generateWeeklyCandidateReport(); 			// optional: PDF, CSV, or just logging
}
