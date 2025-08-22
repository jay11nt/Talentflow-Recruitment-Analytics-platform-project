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

import com.tr.dto.request.InterviewRequestDTO;
import com.tr.dto.response.ApiResponseDTO;
import com.tr.dto.response.InterviewResponseDTO;
import com.tr.service.InterviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/interviews")
@RequiredArgsConstructor
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<InterviewResponseDTO>> scheduleInterview(@Valid @RequestBody  InterviewRequestDTO requestDTO) 
    {
        InterviewResponseDTO saved = interviewService.scheduleInterview(requestDTO);
        return ResponseEntity.ok(ApiResponseDTO.<InterviewResponseDTO>builder()
                .success(true)
                .message("Interview scheduled successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<InterviewResponseDTO>> getInterviewById(@PathVariable Long id) 
    {
        InterviewResponseDTO interview = interviewService.getInterviewById(id);
        return ResponseEntity.ok(ApiResponseDTO.<InterviewResponseDTO>builder()
                .success(true)
                .message("Interview fetched")
                .data(interview)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<ApiResponseDTO<List<InterviewResponseDTO>>> getByCandidate(@PathVariable Long candidateId) 
    {
        List<InterviewResponseDTO> list = interviewService.getInterviewsByCandidate(candidateId);
        return ResponseEntity.ok(ApiResponseDTO.<List<InterviewResponseDTO>>builder()
                .success(true)
                .message("Interviews fetched for candidate")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<ApiResponseDTO<List<InterviewResponseDTO>>> getByJob(@PathVariable Long jobId) 
    {
        List<InterviewResponseDTO> list = interviewService.getInterviewsByJob(jobId);
        return ResponseEntity.ok(ApiResponseDTO.<List<InterviewResponseDTO>>builder()
                .success(true)
                .message("Interviews fetched for job")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<InterviewResponseDTO>> updateInterview(@PathVariable Long id,
    													@Valid @RequestBody  InterviewRequestDTO requestDTO) 
    {
        InterviewResponseDTO updated = interviewService.updateInterview(id, requestDTO);
        return ResponseEntity.ok(ApiResponseDTO.<InterviewResponseDTO>builder()
                .success(true)
                .message("Interview updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> cancelInterview(@PathVariable Long id) 
    {
        interviewService.cancelInterview(id);
        return ResponseEntity.ok(ApiResponseDTO.<Void>builder()
                .success(true)
                .message("Interview cancelled successfully")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
