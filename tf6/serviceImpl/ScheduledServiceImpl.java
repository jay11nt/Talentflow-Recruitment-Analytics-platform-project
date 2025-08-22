package com.tr.serviceImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.tr.entity.Interview;
import com.tr.repository.CandidateRepository;
import com.tr.repository.InterviewRepository;
import com.tr.repository.JobRepository;
import com.tr.service.EmailService;
import com.tr.service.ScheduledService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ScheduledServiceImpl implements ScheduledService {

    private final InterviewRepository interviewRepository;
    private final JobRepository jobRepository;
    private final CandidateRepository candidateRepository;
    private final EmailService emailService;

    // 🕗 Runs daily at 8 AM
    @Override
    @Scheduled(cron = "0 0 8 * * ?")
    public void sendDailyInterviewReminders() {
        LocalDateTime todayStart = LocalDate.now().atStartOfDay();
        LocalDateTime todayEnd = todayStart.plusDays(1);

        List<Interview> interviews = interviewRepository.findAll().stream()
                .filter(i -> i.getScheduledAt() != null &&
                             !i.getScheduledAt().isBefore(todayStart) &&
                             i.getScheduledAt().isBefore(todayEnd))
                .toList();

        for (Interview i : interviews) {
            String candidateEmail = i.getCandidate().getEmail();
            String candidateName = i.getCandidate().getFullName();
            String datetime = i.getScheduledAt().toString();

            emailService.sendInterviewConfirmation(candidateEmail, candidateName, datetime);
            log.info(" Sent interview reminder to {}", candidateEmail);
        }
    }

    // 🧹 Runs weekly (Sunday 3 AM)
    @Override
    @Scheduled(cron = "0 0 3 ? * SUN")
    public void cleanUpClosedJobsOlderThan30Days() {
        LocalDate cutoffDate = LocalDate.now().minusDays(30);

        jobRepository.findAll().stream()
                .filter(job -> job.getStatus() != null
                        && job.getStatus().name().equalsIgnoreCase("CLOSED")
                        && job.getPostedDate() != null
                        && job.getPostedDate().isBefore(cutoffDate))
                .forEach(job -> {
                    jobRepository.delete(job);
                    log.info(" Deleted closed job: {}", job.getJobTitle());
                });
    }

    // 📊 Runs weekly (Monday 10 AM)
    @Override
    @Scheduled(cron = "0 0 10 ? * MON")
    public void generateWeeklyCandidateReport() {
        long total = candidateRepository.count();
        long hired = candidateRepository.findAll().stream()
                .filter(c -> c.getStatus() != null && c.getStatus().name().equalsIgnoreCase("HIRED"))
                .count();

        long conversionRate = total > 0 ? (hired * 100 / total) : 0;

        log.info(" Weekly Report: Total Candidates = {}, Hired = {}, Conversion Rate = {}%",
                total, hired, conversionRate);
    }
}
