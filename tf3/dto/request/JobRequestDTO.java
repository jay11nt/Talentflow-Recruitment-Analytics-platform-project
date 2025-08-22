package com.tr.dto.request;

import java.time.LocalDate;

import com.tr.entity.Job.JobStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobRequestDTO {

    private String jobTitle;

    private String department;

    private String description;

    private String requiredSkills; 	// Comma-separated string (e.g., "Java,Spring Boot")

    private String location;

    private LocalDate postedDate; 	// Optional; can be set in service

    private JobStatus status; 		// Enum: OPEN, CLOSED, ON_HOLD
}
