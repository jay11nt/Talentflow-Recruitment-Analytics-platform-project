package com.tr.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tr.dto.request.InterviewRequestDTO;
import com.tr.dto.response.InterviewResponseDTO;
import com.tr.entity.Candidate;
import com.tr.entity.Interview;
import com.tr.entity.Job;
import com.tr.exception.CandidateNotFoundException;
import com.tr.exception.InterviewConflictException;
import com.tr.exception.JobNotFoundException;
import com.tr.mapper.InterviewMapper;
import com.tr.repository.CandidateRepository;
import com.tr.repository.InterviewRepository;
import com.tr.repository.JobRepository;
import com.tr.service.EmailService;
import com.tr.service.InterviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository interviewRepository;
    private final CandidateRepository candidateRepository;
    private final JobRepository jobRepository;
    private final EmailService emailService;

    @Transactional
    @Override
    public InterviewResponseDTO scheduleInterview(InterviewRequestDTO requestDTO) {

        Candidate candidate = candidateRepository.findById(requestDTO.getCandidateId())
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + requestDTO.getCandidateId()));

        Job job = jobRepository.findById(requestDTO.getJobId())
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + requestDTO.getJobId()));

        boolean exists = interviewRepository.findByCandidateId(candidate.getId()).stream()
                .anyMatch(i -> i.getScheduledAt().equals(requestDTO.getScheduledAt()));

        if (exists) {
            throw new InterviewConflictException("Candidate already has an interview scheduled at this time.");
        }

        Interview interview = InterviewMapper.toEntity(requestDTO, candidate, job);
        Interview saved = interviewRepository.save(interview);

        //  Send interview confirmation email to candidate(TRIGGER)
        System.out.println(">>>>>>>>> Triggering process started interview confirmation email.<<<<<<<<<<");
        emailService.sendInterviewConfirmation(
                candidate.getEmail(),
                candidate.getFullName(),
                requestDTO.getScheduledAt().toString()
        );
        
      try {  
        //  Send alert email to HR
    	  System.out.println(">>>>>>>> HR Notification mail <<<<<<<");
        emailService.sendHRNotification(
                candidate.getFullName(),
                requestDTO.getScheduledAt().toString(),
                requestDTO.getInterviewer());
    		} 
    catch (Exception e) 
    	{
        System.err.println("⚠ Email sending failed: " + e.getMessage());
    	}
        return InterviewMapper.toResponseDTO(saved);
    	}

    @Override
    public InterviewResponseDTO getInterviewById(Long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new InterviewConflictException("Interview not found with ID: " + id));
        return InterviewMapper.toResponseDTO(interview);
    }

    @Override
    public List<InterviewResponseDTO> getInterviewsByCandidate(Long candidateId) {
        return interviewRepository.findByCandidateId(candidateId).stream()
                .map(InterviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<InterviewResponseDTO> getInterviewsByJob(Long jobId) {
        return interviewRepository.findByJobId(jobId).stream()
                .map(InterviewMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InterviewResponseDTO updateInterview(Long id, InterviewRequestDTO requestDTO) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new InterviewConflictException("Interview not found with ID: " + id));

        Candidate candidate = candidateRepository.findById(requestDTO.getCandidateId())
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + requestDTO.getCandidateId()));

        Job job = jobRepository.findById(requestDTO.getJobId())
                .orElseThrow(() -> new JobNotFoundException("Job not found with ID: " + requestDTO.getJobId()));

        interview.setScheduledAt(requestDTO.getScheduledAt());
        interview.setMode(requestDTO.getMode());
        interview.setInterviewer(requestDTO.getInterviewer());
        interview.setFeedback(requestDTO.getFeedback());
        interview.setResult(requestDTO.getResult());
        interview.setCandidate(candidate);
        interview.setJob(job);

        Interview updated = interviewRepository.save(interview);
        return InterviewMapper.toResponseDTO(updated);
    }

    @Override
    public void cancelInterview(Long id) {
        Interview interview = interviewRepository.findById(id)
                .orElseThrow(() -> new InterviewConflictException("Interview not found with ID: " + id));
        interviewRepository.delete(interview);
    }
}
