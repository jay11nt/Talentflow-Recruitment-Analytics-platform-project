package com.tr.dto.response;

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
public class JobResponseDTO {

    private Long id;

    private String jobTitle;

    private String department;

    private String description;

    private String requiredSkills;

    private String location;

    private LocalDate postedDate;

    private JobStatus status;
}
