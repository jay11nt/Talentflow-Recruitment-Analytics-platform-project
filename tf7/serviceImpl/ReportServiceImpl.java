package com.tr.serviceImpl;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tr.entity.Candidate;
import com.tr.repository.CandidateRepository;
import com.tr.repository.InterviewRepository;
import com.tr.repository.JobRepository;
import com.tr.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService 
{

    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final InterviewRepository interviewRepository;

    @Override
    public Map<String, Object> generateSummary() 
    {
        Map<String, Object> summary = new LinkedHashMap<>();

        summary.put("totalCandidates", candidateRepository.count());
        summary.put("totalJobs", jobRepository.count());
        summary.put("totalInterviews", interviewRepository.count());

        long hired = candidateRepository.findAll().stream()
                .filter(c -> c.getStatus().name().equals("HIRED"))
                .count();

        long openJobs = jobRepository.findAll().stream()
                .filter(j -> j.getStatus().name().equals("OPEN"))
                .count();

        summary.put("hiredCandidates", hired);
        summary.put("openJobs", openJobs);

        return summary;
    }

    @Override
    public Map<String, Long> getCandidateStatusCount() 
    {
        return candidateRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        c -> c.getStatus().toString(), // convert Enum to String
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getJobStatusCount() 
    {
        return jobRepository.findAll().stream()
                .collect(Collectors.groupingBy(
                        j -> j.getStatus().toString(), // convert Enum to String
                        Collectors.counting()
                ));
    }

    @Override
    public Map<String, Long> getPopularSkills() 
    {
        Map<String, Long> skillMap = new HashMap<>();

        for (Candidate candidate : candidateRepository.findAll()) 
        {
            if (candidate.getSkills() != null) {
                String[] skills = candidate.getSkills().split(",\\s*");
                for (String skill : skills) {
                    skillMap.put(skill.toLowerCase(),
                            skillMap.getOrDefault(skill.toLowerCase(), 0L) + 1);
                }
            }
        }

        return skillMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }
}
