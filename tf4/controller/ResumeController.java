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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tr.dto.request.ResumeRequestDTO;
import com.tr.dto.response.ApiResponseDTO;
import com.tr.dto.response.ResumeResponseDTO;
import com.tr.service.ResumeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/resumes")
@RequiredArgsConstructor
public class ResumeController {

    private final ResumeService resumeService;

    @PostMapping("/upload/{candidateId}")
    public ResponseEntity<ApiResponseDTO<ResumeResponseDTO>> uploadResume(@PathVariable Long candidateId,
                                                                          @RequestBody @Valid ResumeRequestDTO dto) 
    {
        ResumeResponseDTO saved = resumeService.uploadResume(candidateId, dto);
        return ResponseEntity.ok(ApiResponseDTO.<ResumeResponseDTO>builder()
                .success(true)
                .message("Resume uploaded successfully")
                .data(saved)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/{candidateId}")
    public ResponseEntity<ApiResponseDTO<ResumeResponseDTO>> getResume(@PathVariable Long candidateId) 
    {
        ResumeResponseDTO resume = resumeService.getResumeByCandidateId(candidateId);
        return ResponseEntity.ok(ApiResponseDTO.<ResumeResponseDTO>builder()
                .success(true)
                .message("Resume fetched")
                .data(resume)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping("/update/{candidateId}")
    public ResponseEntity<ApiResponseDTO<ResumeResponseDTO>> updateResume(@PathVariable Long candidateId,
                                                                          @RequestBody @Valid ResumeRequestDTO dto) 
    {
        ResumeResponseDTO updated = resumeService.updateResume(candidateId, dto);
        return ResponseEntity.ok(ApiResponseDTO.<ResumeResponseDTO>builder()
                .success(true)
                .message("Resume updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("/{candidateId}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteResume(@PathVariable Long candidateId) 
    {
        resumeService.deleteResume(candidateId);
        return ResponseEntity.ok(ApiResponseDTO.<Void>builder()
                .success(true)
                .message("Resume deleted successfully")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build());
    }

    //  Advanced: Extract keywords from resume
    @GetMapping("/keywords/{resumeId}")
    public ResponseEntity<ApiResponseDTO<List<String>>> extractKeywords(@PathVariable Long resumeId) 
    {
        List<String> keywords = resumeService.extractKeywords(resumeId);
        return ResponseEntity.ok(ApiResponseDTO.<List<String>>builder()
                .success(true)
                .message("Keywords extracted from resume")
                .data(keywords)
                .timestamp(LocalDateTime.now())
                .build());
    }

    //  Skill match percentage
    @GetMapping("/match-percentage")
    public ResponseEntity<ApiResponseDTO<Double>> getMatchPercentage(@RequestParam Long resumeId,
                                                                      @RequestParam Long jobId) 
    {
        double match = resumeService.calculateSkillMatchPercentage(resumeId, jobId);
        return ResponseEntity.ok(ApiResponseDTO.<Double>builder()
                .success(true)
                .message("Skill match percentage calculated")
                .data(match)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
