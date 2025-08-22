package com.tr.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tr.entity.Resume;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Long> {

    Optional<Resume> findByCandidateId(Long candidateId);

    boolean existsByCandidateId(Long candidateId);
}
