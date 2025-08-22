package com.tr.mapper;

import com.tr.dto.request.ResumeRequestDTO;
import com.tr.dto.response.ResumeResponseDTO;
import com.tr.entity.Candidate;
import com.tr.entity.Resume;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ResumeMapper {

    public Resume toEntity(ResumeRequestDTO dto, Candidate candidate) 	//convert requestdto to entity
    {
        if (dto == null || candidate == null) return null;

        return Resume.builder()
                .content(dto.getContent())
                .fileName(dto.getFileName())
                .uploadDate(java.time.LocalDate.now()) 		// Auto-set during creation
                .candidate(candidate)
                .build();
    }

    public ResumeResponseDTO toResponseDTO(Resume resume) 			//convert entity to responsedto
    {
        if (resume == null) return null;

        return ResumeResponseDTO.builder()
                .id(resume.getId())
                .content(resume.getContent())
                .fileName(resume.getFileName())
                .uploadDate(resume.getUploadDate())
                .candidateId(resume.getCandidate() != null ? resume.getCandidate().getId() : null)
                .build();
    }

    public void updateEntityFromDTO(ResumeRequestDTO dto, Resume resume) 	//updates existing resumes 
    {
        if (dto == null || resume == null) return;

        resume.setContent(dto.getContent());
        resume.setFileName(dto.getFileName());
        // Don't update uploadDate here — keep it as creation timestamp
    }
}
