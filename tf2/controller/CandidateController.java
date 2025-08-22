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

import com.tr.dto.request.CandidateRequestDTO;
import com.tr.dto.response.ApiResponseDTO;
import com.tr.dto.response.CandidateResponseDTO;
import com.tr.entity.Candidate.CandidateStatus;
import com.tr.service.CandidateService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/candidates")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<ApiResponseDTO<CandidateResponseDTO>> createCandidate(@Valid @RequestBody CandidateRequestDTO dto) 
    {
    	//System.out.println(" CandidateController -> createCandidate() called");
        CandidateResponseDTO created = candidateService.createCandidate(dto);
        
        return ResponseEntity.ok(ApiResponseDTO.<CandidateResponseDTO>builder()
                .success(true)
                .message("Candidate created successfully")
                .data(created)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<CandidateResponseDTO>>> getAll() {
        List<CandidateResponseDTO> list = candidateService.getAllCandidates();
        return ResponseEntity.ok(ApiResponseDTO.<List<CandidateResponseDTO>>builder()
                .success(true)
                .message("All candidates fetched")
                .data(list)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CandidateResponseDTO>> getById(@PathVariable Long id) 
    {
        CandidateResponseDTO dto = candidateService.getCandidateById(id);
        return ResponseEntity.ok(ApiResponseDTO.<CandidateResponseDTO>builder()
                .success(true)
                .message("Candidate fetched")
                .data(dto)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<CandidateResponseDTO>> update(@PathVariable Long id, @Valid @RequestBody CandidateRequestDTO dto) 
    {
        CandidateResponseDTO updated = candidateService.updateCandidate(id, dto);
        return ResponseEntity.ok(ApiResponseDTO.<CandidateResponseDTO>builder()
                .success(true)
                .message("Candidate updated successfully")
                .data(updated)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> delete(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.ok(ApiResponseDTO.<Void>builder()
                .success(true)
                .message("Candidate deleted successfully")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/search")		//serch candidate byskill
    public ResponseEntity<ApiResponseDTO<List<CandidateResponseDTO>>> searchBySkill(@RequestParam String keyword) 
    {
        List<CandidateResponseDTO> result = candidateService.searchCandidatesBySkills(keyword);
        return ResponseEntity.ok(ApiResponseDTO.<List<CandidateResponseDTO>>builder()
                .success(true)
                .message("Skill-based candidates fetched")
                .data(result)
                .timestamp(LocalDateTime.now())
                .build());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<ApiResponseDTO<List<CandidateResponseDTO>>> getByStatus(@PathVariable CandidateStatus status) 
    {
        List<CandidateResponseDTO> result = candidateService.getCandidatesByStatus(status);
        return ResponseEntity.ok(ApiResponseDTO.<List<CandidateResponseDTO>>builder()
                .success(true)
                .message("Status-based candidates fetched")
                .data(result)
                .timestamp(LocalDateTime.now())
                .build());
    }
}
