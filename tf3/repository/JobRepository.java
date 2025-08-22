package com.tr.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tr.entity.Job;

@Repository
public interface JobRepository extends JpaRepository<Job, Long> 
{

    List<Job> findByStatus(Job.JobStatus status);

    boolean existsByJobTitle(String jobTitle);
}
