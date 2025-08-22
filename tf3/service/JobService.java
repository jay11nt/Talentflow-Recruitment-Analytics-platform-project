package com.tr.service;

import java.util.List;

import com.tr.dto.request.JobRequestDTO;
import com.tr.dto.response.JobResponseDTO;

public interface JobService {

    JobResponseDTO createJob(JobRequestDTO requestDTO);

    JobResponseDTO getJobById(Long id);

    List<JobResponseDTO> getAllJobs();

    JobResponseDTO updateJob(Long id, JobRequestDTO requestDTO);

    void deleteJob(Long id);

    List<JobResponseDTO> getJobsByStatus(String status);  		// Optional enhancement
}
