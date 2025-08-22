package com.tr.dto.request;

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
public class InterviewRequestDTO {

    private LocalDateTime scheduledAt;

    private InterviewMode mode; 		// Enum: ONLINE, OFFLINE, TELEPHONIC

    private String interviewer;

    private String feedback; 			// Optional

    private CandidateResult result; 	// Enum: PASSED, FAILED, PENDING

    private Long candidateId; 			// Foreign key reference

    private Long jobId; 				// Foreign key reference
}
