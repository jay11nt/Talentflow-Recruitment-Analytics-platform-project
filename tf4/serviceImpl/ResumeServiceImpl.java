package com.tr.serviceImpl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.tr.dto.request.ResumeRequestDTO;
import com.tr.dto.response.ResumeResponseDTO;
import com.tr.entity.Candidate;
import com.tr.entity.Job;
import com.tr.entity.Resume;
import com.tr.exception.CandidateNotFoundException;
import com.tr.exception.JobNotFoundException;
import com.tr.exception.ResumeNotFoundException;
import com.tr.mapper.ResumeMapper;
import com.tr.repository.CandidateRepository;
import com.tr.repository.JobRepository;
import com.tr.repository.ResumeRepository;
import com.tr.service.ResumeService;
import com.tr.util.KeywordMatcher;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ResumeServiceImpl implements ResumeService {

    private final ResumeRepository resumeRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;

    @Override
    public ResumeResponseDTO uploadResume(Long candidateId, ResumeRequestDTO requestDTO) {

        Candidate candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + candidateId));

        Resume resume = ResumeMapper.toEntity(requestDTO, candidate);
        resume.setCandidate(candidate);
        resume.setUploadDate(LocalDate.now());

        Resume saved = resumeRepository.save(resume);
        return ResumeMapper.toResponseDTO(saved);
    }

    @Override
    public ResumeResponseDTO getResumeByCandidateId(Long candidateId) {
        Resume resume = resumeRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found for candidate ID: " + candidateId));
        return ResumeMapper.toResponseDTO(resume);
    }

    @Override
    public ResumeResponseDTO updateResume(Long candidateId, ResumeRequestDTO requestDTO) {
        Resume resume = resumeRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found for candidate ID: " + candidateId));

        resume.setContent(requestDTO.getContent());
        resume.setFileName(requestDTO.getFileName());
        resume.setUploadDate(LocalDate.now());

        Resume updated = resumeRepository.save(resume);
        return ResumeMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteResume(Long candidateId) {
        Resume resume = resumeRepository.findByCandidateId(candidateId)
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found for candidate ID: " + candidateId));
        resumeRepository.delete(resume);
    }

    @Override
    public List<String> extractKeywords(Long resumeId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found with ID: " + resumeId));

        String content = resume.getContent();
        if (content == null || content.isBlank()) {
            return List.of(); // return empty list instead of error
        }

        return KeywordMatcher.extractKeywords(content);
    }

    @Override
    public double calculateSkillMatchPercentage(Long resumeId, Long jobId) {
        Resume resume = resumeRepository.findById(resumeId)
                .orElseThrow(() -> new ResumeNotFoundException("Resume not found with ID: " + resumeId));

        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + jobId));

        String resumeContent = resume.getContent();             // Raw resume text
        String jobSkills = job.getRequiredSkills();             // Comma-separated job skills list

        List<String> jobSkillList = Arrays.asList(jobSkills.split(",\\s*"));           		// job posted skill list ["Java", "Spring"]
        List<String> candidateSkillList = KeywordMatcher.extractKeywords(resumeContent); 	// candidate having skill ["java", "spring", "hibernate"]

        return KeywordMatcher.calculateMatchPercentage(jobSkillList, candidateSkillList);	//both match here
    }

//    @Override
//    public String downloadResumeContent(Long resumeId) {
//        Resume resume = resumeRepository.findById(resumeId)
//                .orElseThrow(() -> new ResumeNotFoundException("Resume not found with ID: " + resumeId));
//        return resume.getContent();
//    }
//
//    @Override
//    public boolean hasResume(Long candidateId) {
//        return resumeRepository.findByCandidateId(candidateId).isPresent();
//    }
}
