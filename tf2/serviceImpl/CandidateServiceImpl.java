package com.tr.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.tr.dto.request.CandidateRequestDTO;
import com.tr.dto.response.CandidateResponseDTO;
import com.tr.entity.Candidate;
import com.tr.entity.Candidate.CandidateStatus;
import com.tr.exception.CandidateNotFoundException;
import com.tr.mapper.CandidateMapper;
import com.tr.repository.CandidateRepository;
import com.tr.service.CandidateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CandidateServiceImpl implements CandidateService {

    private final CandidateRepository candidateRepository;

    @Override
    public CandidateResponseDTO createCandidate(CandidateRequestDTO requestDTO) 
    {
        // Email duplication check
        if (candidateRepository.existsByEmail(requestDTO.getEmail())) {
            throw new IllegalArgumentException("Candidate with email already exists: " + requestDTO.getEmail());
        }

        Candidate candidate = CandidateMapper.toEntity(requestDTO);
        Candidate saved = candidateRepository.save(candidate);
        return CandidateMapper.toResponseDTO(saved);
    }

    @Override
    public CandidateResponseDTO getCandidateById(Long id) 
    {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + id));
        return CandidateMapper.toResponseDTO(candidate);
    }

    @Override
    public List<CandidateResponseDTO> getAllCandidates() 
    {
        return candidateRepository.findAll()
                .stream()
                .map(CandidateMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CandidateResponseDTO updateCandidate(Long id, CandidateRequestDTO requestDTO) 
    {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + id));

        candidate.setFullName(requestDTO.getFullName());
        candidate.setEmail(requestDTO.getEmail());
        candidate.setContact(requestDTO.getContact());
        candidate.setLocation(requestDTO.getLocation());
        candidate.setSkills(requestDTO.getSkills());
        candidate.setStatus(requestDTO.getStatus());
        candidate.setApplicationDate(requestDTO.getApplicationDate());
        candidate.setGender(requestDTO.getGender());

        Candidate updated = candidateRepository.save(candidate);
        return CandidateMapper.toResponseDTO(updated);
    }

    @Override
    public void deleteCandidate(Long id) 
    {
        Candidate candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new CandidateNotFoundException("Candidate not found with ID: " + id));
        candidateRepository.delete(candidate);
    }

    @Override
    public List<CandidateResponseDTO> searchCandidatesBySkills(String skillKeyword) 
    {
        return candidateRepository.findAll().stream()
                .filter(c -> c.getSkills() != null && c.getSkills().toLowerCase().contains(skillKeyword.toLowerCase()))
                .map(CandidateMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<CandidateResponseDTO> getCandidatesByStatus(CandidateStatus status) 
    {
        return candidateRepository.findAll().stream()
                .filter(c -> c.getStatus() != null && c.getStatus() == status)
                .map(CandidateMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
