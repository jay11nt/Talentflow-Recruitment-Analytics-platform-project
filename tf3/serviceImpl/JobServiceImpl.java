package com.tr.serviceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tr.dto.request.JobRequestDTO;
import com.tr.dto.response.JobResponseDTO;
import com.tr.entity.Job;
import com.tr.exception.JobNotFoundException;
import com.tr.mapper.JobMapper;
import com.tr.repository.JobRepository;
import com.tr.service.JobService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor									//for injection
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    @Override
    public JobResponseDTO createJob(JobRequestDTO requestDTO) {
        Job job = JobMapper.toEntity(requestDTO);
        job.setPostedDate(LocalDate.now()); 				// Automatically set current date
        Job saved = jobRepository.save(job);
        return JobMapper.toResponseDTO(saved);
    }

    @Override
    public JobResponseDTO getJobById(Long id) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        return JobMapper.toResponseDTO(job);
    }

    @Override
    public List<JobResponseDTO> getAllJobs() {
        return jobRepository.findAll()
                .stream()
                .map(JobMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public JobResponseDTO updateJob(Long id, JobRequestDTO requestDTO) {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));

        job.setJobTitle(requestDTO.getJobTitle());
        job.setDepartment(requestDTO.getDepartment());
        job.setDescription(requestDTO.getDescription());
        job.setRequiredSkills(requestDTO.getRequiredSkills());
        job.setLocation(requestDTO.getLocation());
        job.setStatus(requestDTO.getStatus());

        Job updated = jobRepository.save(job);
        return JobMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteJob(Long id) 
    {
        Job job = jobRepository.findById(id)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + id));
        jobRepository.delete(job);
    }

    @Override
    public List<JobResponseDTO> getJobsByStatus(String status) {
        return jobRepository.findAll().stream()
                .filter(job -> job.getStatus() != null && job.getStatus().name().equalsIgnoreCase(status))
                .map(JobMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
