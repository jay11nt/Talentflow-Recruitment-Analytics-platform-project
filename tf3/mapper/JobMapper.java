package com.tr.mapper;

import com.tr.dto.request.JobRequestDTO;
import com.tr.dto.response.JobResponseDTO;
import com.tr.entity.Job;

import lombok.experimental.UtilityClass;

@UtilityClass
public class JobMapper {

    public Job toEntity(JobRequestDTO dto) 
    {
        if (dto == null) return null;

        return Job.builder()
                .jobTitle(dto.getJobTitle())
                .department(dto.getDepartment())
                .description(dto.getDescription())
                .requiredSkills(dto.getRequiredSkills())
                .location(dto.getLocation())
                .postedDate(dto.getPostedDate())
                .status(dto.getStatus())
                .build();
    }

    public JobResponseDTO toResponseDTO(Job job) 
    {
        if (job == null) return null;

        return JobResponseDTO.builder()
                .id(job.getId())
                .jobTitle(job.getJobTitle())
                .department(job.getDepartment())
                .description(job.getDescription())
                .requiredSkills(job.getRequiredSkills())
                .location(job.getLocation())
                .postedDate(job.getPostedDate())
                .status(job.getStatus())
                .build();
    }

    public void updateEntityFromDTO(JobRequestDTO dto, Job job) 
    {
        if (dto == null || job == null) return;

        job.setJobTitle(dto.getJobTitle());
        job.setDepartment(dto.getDepartment());
        job.setDescription(dto.getDescription());
        job.setRequiredSkills(dto.getRequiredSkills());
        job.setLocation(dto.getLocation());
        job.setPostedDate(dto.getPostedDate());
        job.setStatus(dto.getStatus());
    }
}
