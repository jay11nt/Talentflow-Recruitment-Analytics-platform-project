package com.tr.service;

public interface EmailService {

    void sendEmail(String to, String subject, String body);

    void sendInterviewConfirmation(String candidateEmail, String candidateName, String interviewDateTime);

    void sendApplicationStatusUpdate(String candidateEmail, String status); 	// Hired / Rejected / On Hold

    void sendResumeReceivedNotification(String candidateEmail);
    
    void sendHRNotification( String candidateName, String interviewDateTime, String interviewer);
}
