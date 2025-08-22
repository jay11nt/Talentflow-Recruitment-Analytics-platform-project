package com.tr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tr.entity.Candidate;

@Repository
public interface CandidateRepository extends JpaRepository<Candidate, Long> 
{

    Optional<Candidate> findByEmail(String email); 	// for checking duplicates or login features

    boolean existsByEmail(String email); 			// useful for validations
}
