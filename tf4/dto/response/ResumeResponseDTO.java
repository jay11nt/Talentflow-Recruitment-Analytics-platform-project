package com.tr.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeResponseDTO {

    private Long id;

    private String content;

    private String fileName;

    private LocalDate uploadDate;

    private Long candidateId; 			// Reference only (optional)
}
