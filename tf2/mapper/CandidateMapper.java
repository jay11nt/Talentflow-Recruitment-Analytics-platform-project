package com.tr.mapper;

import com.tr.dto.request.CandidateRequestDTO;
import com.tr.dto.response.CandidateResponseDTO;
import com.tr.entity.Candidate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CandidateMapper {

    public Candidate toEntity(CandidateRequestDTO dto) 
    {
        if (dto == null) return null;

        return Candidate.builder()
                .fullName(dto.getFullName())
                .gender(dto.getGender())
                .email(dto.getEmail())
                .contact(dto.getContact())
                .location(dto.getLocation())
                .skills(dto.getSkills())
                .applicationDate(dto.getApplicationDate())
                .status(dto.getStatus())
                .build();
    }

    public CandidateResponseDTO toResponseDTO(Candidate candidate) 
    {
        if (candidate == null) return null;

        return CandidateResponseDTO.builder()
                .id(candidate.getId())
                .fullName(candidate.getFullName())
                .gender(candidate.getGender())
                .email(candidate.getEmail())
                .contact(candidate.getContact())
                .location(candidate.getLocation())
                .skills(candidate.getSkills())
                .applicationDate(candidate.getApplicationDate())
                .status(candidate.getStatus())
                .build();
    }

    // Optional: Use this for PUT operations to avoid overwriting whole entity
    public void updateEntityFromDTO(CandidateRequestDTO dto, Candidate candidate) 
    {
        if (dto == null || candidate == null) return;

        candidate.setFullName(dto.getFullName());
        candidate.setGender(dto.getGender());
        candidate.setEmail(dto.getEmail());
        candidate.setContact(dto.getContact());
        candidate.setLocation(dto.getLocation());
        candidate.setSkills(dto.getSkills());
        candidate.setApplicationDate(dto.getApplicationDate());
        candidate.setStatus(dto.getStatus());
    }
}
