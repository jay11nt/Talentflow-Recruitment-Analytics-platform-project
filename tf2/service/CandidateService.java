package com.tr.service;

import java.util.List;

import com.tr.dto.request.CandidateRequestDTO;
import com.tr.dto.response.CandidateResponseDTO;
import com.tr.entity.Candidate.CandidateStatus;

public interface CandidateService {

    CandidateResponseDTO createCandidate(CandidateRequestDTO requestDTO);

    CandidateResponseDTO getCandidateById(Long id);

    List<CandidateResponseDTO> getAllCandidates();

    CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO requestDTO);

    void deleteCandidate(Long id);

    
    List<CandidateResponseDTO> searchCandidatesBySkills(String skillKeyword);		//search by skill
    List<CandidateResponseDTO> getCandidatesByStatus(CandidateStatus status);		//get by status 


    //sendInterviewNotificationEmail(Long candidateId)
}