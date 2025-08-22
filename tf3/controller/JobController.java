package com.tr.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tr.dto.request.JobRequestDTO;
import com.tr.dto.response.ApiResponseDTO;
import com.tr.dto.response.JobResponseDTO;
import com.tr.service.JobService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<JobResponseDTO>> createJob(@RequestBody @Valid JobRequestDTO dto) {
        JobResponseDTO created = jobService.createJob(dto);
        return ResponseEntity.ok(ApiResponseDTO.<JobResponseDTO>builder()
                .success(true)
                .message("Job created successfully")
                .data(created)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<JobResponseDTO>> getJobById(@PathVariable Long id) {
        JobResponseDTO job = jobService.getJobById(id);
        return ResponseEntity.ok(ApiResponseDTO.<JobResponseDTO>builder()
                .success(true)
                .message("Job fetched successfully")
                .data(job)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<JobResponseDTO>>> getAllJobs() {
        List<JobResponseDTO> jobs = jobService.getAllJobs();
        return ResponseEntity.ok(ApiResponseDTO.<List<JobResponseDTO>>builder()
                .success(true)
                .message("All jobs fetched")
                .data(jobs)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<JobResponseDTO>> updateJob(@PathVariable Long id,
                                                                     @RequestBody @Valid JobRequestDTO dto) {
        JobResponseDTO updated = jobService.updateJob(id, dto);
        return ResponseEntity.ok(ApiResponseDTO.<JobResponseDTO>builder()
                .success(true)
                .message("Job updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteJob(@PathVariable Long id) {
        jobService.deleteJob(id);
        return ResponseEntity.ok(ApiResponseDTO.<Void>builder()
                .success(true)
                .message("Job deleted successfully")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponseDTO<List<JobResponseDTO>>> getJobsByStatus(@PathVariable String status) {
        List<JobResponseDTO> jobs = jobService.getJobsByStatus(status);
        return ResponseEntity.ok(ApiResponseDTO.<List<JobResponseDTO>>builder()
                .success(true)
                .message("Jobs filtered by status")
                .data(jobs)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
