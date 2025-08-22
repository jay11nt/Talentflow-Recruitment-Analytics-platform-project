package com.tr.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.tr.dto.response.ApiResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CandidateNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleCandidateNotFound(CandidateNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleJobNotFound(JobNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(InterviewConflictException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleInterviewConflict(InterviewConflictException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
    }

    @ExceptionHandler(ResumeNotFoundException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleResumeNotFound(ResumeNotFoundException ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleIllegalArgument(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponseDTO<?>> handleAuthentication(AuthenticationException ex) {
        return buildErrorResponse(HttpStatus.UNAUTHORIZED, "Authentication failed");
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseDTO<?>> handleGlobalException(Exception ex, WebRequest request) 
    {
    	ex.printStackTrace();		//shows if problem print in console
    	log.error(" Unexpected Error: ", ex); 
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "something went wrong");
    }

   
    
    private ResponseEntity<ApiResponseDTO<?>> buildErrorResponse(HttpStatus status, String message) {
        ApiResponseDTO<?> response = ApiResponseDTO.builder()
                .success(false)
                .message(message)
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();

        return new ResponseEntity<>(response, status);
    }
}
