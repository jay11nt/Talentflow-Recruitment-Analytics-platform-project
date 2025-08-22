package com.tr.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResumeRequestDTO {

    private String content;     // Parsed resume text

    private String fileName;    // Optional: original uploaded file name

    private Long candidateId;   // Required to associate with a candidate
}
