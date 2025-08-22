package com.tr.service;

import java.util.List;

import com.tr.dto.request.ResumeRequestDTO;
import com.tr.dto.response.ResumeResponseDTO;

public interface ResumeService {

    ResumeResponseDTO uploadResume(Long candidateId, ResumeRequestDTO requestDTO);

    ResumeResponseDTO getResumeByCandidateId(Long candidateId);

    ResumeResponseDTO updateResume(Long candidateId, ResumeRequestDTO requestDTO);

    void deleteResume(Long candidateId);

    
    //  Advanced Features
    List<String> extractKeywords(Long resumeId);

    double calculateSkillMatchPercentage(Long resumeId, Long jobId);

    
    
    //String downloadResumeContent(Long resumeId);
    //boolean hasResume(Long candidateId);
}
