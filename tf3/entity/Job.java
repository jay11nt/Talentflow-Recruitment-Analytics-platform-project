package com.tr.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Job title is required")
    private String jobTitle;

    private String department;

    @Lob
    private String description;

    @Column(nullable = false)
    @NotNull(message = "Required skills must be provided")
    private String requiredSkills; // comma-separated

    private String location;

    private LocalDate postedDate;

    public enum JobStatus { OPEN, CLOSED, ON_HOLD }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Job status is required")
    private JobStatus status;
}
