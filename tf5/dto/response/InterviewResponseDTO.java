package com.tr.dto.response;

import java.time.LocalDateTime;

import com.tr.entity.Interview.CandidateResult;
import com.tr.entity.Interview.InterviewMode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InterviewResponseDTO {

    private Long id;

    private LocalDateTime scheduledAt;

    private InterviewMode mode;

    private String interviewer;

    private String feedback;

    private CandidateResult result;

    private Long candidateId;

    private Long jobId;

    // Optional enhancements:---
    // private String candidateName;
    // private String jobTitle;
}
