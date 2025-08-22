package com.tr.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime scheduledAt;		//interview_datetime

    public enum InterviewMode{ONLINE, OFFLINE, TELEPHONIC}
    @Enumerated(EnumType.STRING)
    @NotNull(message = "mode can not be null")
    private InterviewMode mode; 					

    @NotNull(message = "field cannot be empty")
    private String interviewer;

    private String feedback;

    public enum CandidateResult{PASSED, FAILED, PENDING}
    @Enumerated(EnumType.STRING)
    @NotNull(message = "result must be enter here")
    private CandidateResult result; 					

    //mappings
    @ManyToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private Job job;
}
