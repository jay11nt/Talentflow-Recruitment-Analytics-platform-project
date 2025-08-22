package com.tr.dto.request;

import java.time.LocalDate;

import com.tr.entity.Candidate.CandidateStatus;
import com.tr.entity.Candidate.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateRequestDTO {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotNull(message = "Gender is required")
    private Gender gender; 					// MALE, FEMALE, OTHER

    @Email(message = "Email is invalid")
    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^[+]?[0-9]{10,15}$", message = "Contact must be a valid number")
    private String contact;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Skills are required")
    private String skills; 			// comma-separated

    private LocalDate applicationDate; // Optional — can be set in service layer if null

    @NotNull(message = "Status is required")
    private CandidateStatus status; 		// APPLIED, INTERVIEWED, HIRED, REJECTED
}
