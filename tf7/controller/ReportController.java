package com.tr.controller;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tr.dto.response.ApiResponseDTO;
import com.tr.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/summary")
    public ResponseEntity<ApiResponseDTO<Map<String, Object>>> getSummary() {
        Map<String, Object> report = reportService.generateSummary();
        return ResponseEntity.ok(ApiResponseDTO.<Map<String, Object>>builder()
                .success(true)
                .message("Report summary generated")
                .data(report)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/candidates-by-status")
    public ResponseEntity<ApiResponseDTO<Map<String, Long>>> getCandidateStatusReport() {
        Map<String, Long> stats = reportService.getCandidateStatusCount();
        return ResponseEntity.ok(ApiResponseDTO.<Map<String, Long>>builder()
                .success(true)
                .message("Candidate status breakdown")
                .data(stats)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/job-status")
    public ResponseEntity<ApiResponseDTO<Map<String, Long>>> getJobStatusReport() {
        Map<String, Long> stats = reportService.getJobStatusCount();
        return ResponseEntity.ok(ApiResponseDTO.<Map<String, Long>>builder()
                .success(true)
                .message("Job status breakdown")
                .data(stats)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/skill-frequency")
    public ResponseEntity<ApiResponseDTO<Map<String, Long>>> getSkillReport() {
        Map<String, Long> skills = reportService.getPopularSkills();
        return ResponseEntity.ok(ApiResponseDTO.<Map<String, Long>>builder()
                .success(true)
                .message("Skill frequency extracted from candidate profiles")
                .data(skills)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
