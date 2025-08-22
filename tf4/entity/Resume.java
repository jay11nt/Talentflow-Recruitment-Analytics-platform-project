package com.tr.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
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
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Column(nullable = false)
    @NotNull(message = "Resume content cannot be empty")
    private String content; 								// Parsed resume text

    private String fileName; 								// Optional: original file name

    private LocalDate uploadDate;

    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false, unique = true)
    @NotNull(message = "Candidate association is required")
    private Candidate candidate;
    
}
