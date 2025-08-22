package com.tr.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.tr.service.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}") 		//  Reads from application.properties
    private String fromEmail;
    
    @Value("${hr.notification.email}")
    private String hrEmail;
    
    @Override
    public void sendEmail(String to, String subject, String body) 
    {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        message.setFrom(fromEmail);  		// use same as configured in properties
        
        //print email in console
        System.out.println("✅ Email Triggered:");
        System.out.println("To      : " + to);
        System.out.println("From    : " + fromEmail);
        System.out.println("Subject : " + subject);
        System.out.println("Body    : \n" + body);
        System.out.println("Timestamp: " + java.time.LocalDateTime.now());

        mailSender.send(message);
    }

    @Override
    public void sendInterviewConfirmation(String candidateEmail, String candidateName, String interviewDateTime) 
    {
        String subject = "Interview Scheduled - TalentFlow";
        String body = String.format("""
                Dear %s,

                Your interview has been scheduled on %s.
                Please be prepared or if offline pls arrive on time.

                Regards,
                TalentFlow Recruitment Team
                """, candidateName, interviewDateTime);

        sendEmail(candidateEmail, subject, body);
    }

    @Override
    public void sendApplicationStatusUpdate(String candidateEmail, String status) 
    {
        String subject = "Application Status Update - TalentFlow";
        String body = String.format("""
                Hello,

                Your application status has been updated to: %s.

                Thank you for applying with us.

                - TalentFlow Recruitment
                """, status.toUpperCase());

        sendEmail(candidateEmail, subject, body);
    }

    @Override
    public void sendResumeReceivedNotification(String candidateEmail) 
    {
        String subject = "Resume Received - TalentFlow";
        String body = """
                Hello,

                We’ve received your resume successfully.
                Our team will review and get back to you soon.

                Thank you,
                TalentFlow Recruitment
                """;

        sendEmail(candidateEmail, subject, body);
    }

    @Override
    public void sendHRNotification( String candidateName, String interviewDateTime, String interviewer) {
        String subject = "Interview Scheduled Confirmation";
        String body = String.format("""
                Dear HR,

                You have successfully scheduled an interview.

                Candidate: %s
                Scheduled At: %s
                Interviewer: %s

                Regards,
                TalentFlow System
                """, candidateName, interviewDateTime, interviewer);

        sendEmail(hrEmail, subject, body);
    }
}
