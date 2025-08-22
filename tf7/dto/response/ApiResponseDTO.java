package com.tr.dto.response;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponseDTO<T> {

    private boolean success;		//true or false for json response

    private String message;

    private T data; 				// Generic data of any type: Candidate, Job, List<Interview>, etc.
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
}



//-------------we use this for clean Json Response like this to frontend-----
// 	{
//		"success": true,
//		"message": "Candidate created successfully",
//		"data": {
//  				"id": 1,
//  				"fullName": "John Doe",
//  				...
//				}
//	}


//----------in Controller we write like this------------
// 		return ResponseEntity.ok(
//		ApiResponseDTO.builder()
//		.success(true)
//		.message("Candidate fetched successfully")
//		.data(candidateResponseDTO)
//		.build()
//		);