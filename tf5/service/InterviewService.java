package com.tr.service;

import java.util.List;

import com.tr.dto.request.InterviewRequestDTO;
import com.tr.dto.response.InterviewResponseDTO;

public interface InterviewService {

    InterviewResponseDTO scheduleInterview(InterviewRequestDTO requestDTO);

    InterviewResponseDTO getInterviewById(Long id);

    List<InterviewResponseDTO> getInterviewsByCandidate(Long candidateId);

    List<InterviewResponseDTO> getInterviewsByJob(Long jobId);

    InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO requestDTO);

    void cancelInterview(Long id);
}
