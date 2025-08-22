package com.tr.entity;

import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotNull(message = "Name cannot be null")
    @Size(min = 3, max = 15, message = "First name must be between 3 to 15 letters")
    private String fullName;

    public enum Gender{ MALE, FEMALE, OTHER }
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Gender cannot be null")
    private Gender gender;
    
    @Column(nullable = false, unique = true)
    @NotNull(message = "Email cannot be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Column(unique = true)
    @NotNull(message = "Phone number must be 10 digit")
    @Pattern(regexp = "^\\+?[1-9]\\d{9,14}$", message = "Invalid phone number format")
    private String contact;

    @NotNull(message = "enter location")
    private String location;

    @NotNull(message = "Enter the skills")
    private String skills; 					// comma-separated (ex- "Java,Spring Boot,SQL")

    private LocalDate applicationDate;

    public enum CandidateStatus{APPLIED, INTERVIEWED, HIRED, REJECTED} 
    @Enumerated(EnumType.STRING)
    @NotNull(message = "status cannot empty")
    private CandidateStatus status; 			

    
    
    // One-to-One with Resume (optional until resume added)
    @OneToOne(mappedBy = "candidate", cascade = CascadeType.ALL)
    private Resume resume;
}
