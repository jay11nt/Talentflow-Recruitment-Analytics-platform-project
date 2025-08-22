package com.tr.dto.response;

import java.time.LocalDate;

import com.tr.entity.Candidate.CandidateStatus;
import com.tr.entity.Candidate.Gender;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateResponseDTO {

    private Long id;

    private String fullName;

    private Gender gender; 					// Enum value (MALE, FEMALE, OTHER)

    private String email;

    private String contact;

    private String location;

    private String skills;

    private LocalDate applicationDate;

    private CandidateStatus status; 		// Enum value (APPLIED, HIRED, etc.)

    // Optional nested object (uncomment when ready)
    // private ResumeResponseDTO resume;
}
