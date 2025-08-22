package com.tr.mapper;

import com.tr.dto.request.InterviewRequestDTO;
import com.tr.dto.response.InterviewResponseDTO;
import com.tr.entity.Candidate;
import com.tr.entity.Interview;
import com.tr.entity.Job;

import lombok.experimental.UtilityClass;

@UtilityClass
public class InterviewMapper {

    public Interview toEntity(InterviewRequestDTO dto, Candidate candidate, Job job) {
        if (dto == null || candidate == null || job == null) return null;

        return Interview.builder()
                .scheduledAt(dto.getScheduledAt())
                .mode(dto.getMode())
                .interviewer(dto.getInterviewer())
                .feedback(dto.getFeedback())
                .result(dto.getResult())
                .candidate(candidate)
                .job(job)
                .build();
    }

    public InterviewResponseDTO toResponseDTO(Interview interview) {
        if (interview == null) return null;

        return InterviewResponseDTO.builder()
                .id(interview.getId())
                .scheduledAt(interview.getScheduledAt())
                .mode(interview.getMode())
                .interviewer(interview.getInterviewer())
                .feedback(interview.getFeedback())
                .result(interview.getResult())
                .candidateId(interview.getCandidate() != null ? interview.getCandidate().getId() : null)
                .jobId(interview.getJob() != null ? interview.getJob().getId() : null)
                .build();
    }

    public void updateEntityFromDTO(InterviewRequestDTO dto, Interview interview) {
        if (dto == null || interview == null) return;

        interview.setScheduledAt(dto.getScheduledAt());
        interview.setMode(dto.getMode());
        interview.setInterviewer(dto.getInterviewer());
        interview.setFeedback(dto.getFeedback());
        interview.setResult(dto.getResult());
        // Updating candidate/job should be done separately in service if needed
    }
}
