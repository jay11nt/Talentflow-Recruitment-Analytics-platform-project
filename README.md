# TalentFlow Recruitment Analytics Platform project 
A smart backend system for managing candidates, jobs, interviews, resumes, and analytics for modern HR teams.

## 💻 💼 Talentflow recruitment management system:-
**TalentFlow** is a backend-powered recruitment analytics platform designed for HR and hiring teams. It enables seamless candidate tracking, job management, smart resume parsing, interview scheduling, and insightful reports — all built with **Java Spring Boot**.

## ✨ Features :
- ✅ **Candidate Management**  
  Create, update, delete, search candidates by skills or status.

- ✅ **Job Management**  
  Post, update, and close job opportunities with required skills.

- ✅ **Resume Parsing & Keyword Extraction**  
  Extract skills from resume text and match with job requirements.

- ✅ **Interview Scheduling & Notifications**  
  Schedule interviews, track status, and send email confirmations.

- ✅ **Dashboard Reports**  
  Total counts, skill demand, and candidate/job status breakdown.

- ✅ **Email Notifications**  
  Auto-email candidates when interviews are scheduled.

- ✅ **Scheduled Tasks**  
  (Pluggable) Run daily or weekly reports or reminders.

## 🧰 Tech Stack :-

| Layer           | Technology                      |
|----------------|----------------------------------|
| **Backend**     | Java 17, Spring Boot 3.x         |
| **Build Tool**  | Maven                            |
| **Database**    | MySQL                            |
| **ORM**         | Spring Data JPA (Hibernate)      |
| **Validation**  | Jakarta Validation API           |
| **Email**       | JavaMail API                     |
| **Scheduling**  | Spring `@Scheduled`              |
| **Testing**     | JUnit, Mockito *(Optional)*      |

## 🗂️ Project Structure :-
    src/main/java/com/talentflow/
    ├── TalentFlowApplication.java                 // Main Spring Boot class   
    ├── config/
    │   └── SchedulingConfig.java                 // @EnableScheduling config 
    
    ├── entity/
    │   ├── Candidate.java                        // JPA entities
    │   ├── Job.java
    │   └── Interview.java
    │   └── Resume.java  
    
    ├── dto/
    │   ├── request/
    │   │   ├── CandidateRequestDTO.java          // Create/Update requests
    │   │   ├── JobRequestDTO.java
    │   │   └── InterviewRequestDTO.java
    │   │    └── ResumeRequestDTO.java
    │   └── response/
    │       ├── CandidateResponseDTO.java         // API responses
    │       ├── JobResponseDTO.java
    │       ├── InterviewResponseDTO.java
    │       ├── ResumeResponseDTO.java    
    │       └── ApiResponseDTO.java               // Generic response wrapper 
    
    ├── mapper/
    │   ├── CandidateMapper.java
    │   ├── JobMapper.java
    │   ├── InterviewMapper.java
    │   └── ResumeMapper.java		//DTO entity conversion  
    
    ├── repository/
    │   ├── CandidateRepository.java              // JPA repositories
    │   ├── JobRepository.java
    │   └── InterviewRepository.java
    │   └── ResumeRepository.java
    
    ├── service/
    │   ├── CandidateService.java                 // Service interfaces
    │   ├── JobService.java
    │   ├── InterviewService.java
    │   ├── EmailService.java
    │   └── ScheduledService.java
    │   ├── ResumeService.java  
    
    ├── service/impl/
    │   ├── CandidateServiceImpl.java             // Service implementations
    │   ├── JobServiceImpl.java
    │   ├── InterviewServiceImpl.java
    │   ├── EmailServiceImpl.java
    │   └── ScheduledServiceImpl.java
    │   ├── ResumeServiceImpl.java    
    
    ├── controller/
    │   ├── CandidateController.java              // REST controllers
    │   ├── JobController.java
    │   ├── InterviewController.java
    │   └── ReportController.java
    │  ├── ResumeController.java  
    
    ├── exception/
    │   ├── GlobalExceptionHandler.java           // @ControllerAdvice
    │   ├── CandidateNotFoundException.java       // Custom exceptions
    │   ├── JobNotFoundException.java
    │   └── InterviewConflictException.java
         └── ResumeNotFoundException.java
    
    └── util/
        ├── EmailUtil.java                        // Utility classes (logic helper classes)
        ├── KeywordMatcher.java
        └── DateUtil.java

## ⚙️ Setup Instructions :-

### 1. Prerequisites

- Java 17+
- Maven 3.8+
- MySQL running on `localhost:3306`
- Any IDE (IntelliJ, VSCode, Eclipse)

1️ Clone this Repository-
  
    git clone https://github.com/your-username/Talentflow-Recruitment-Analytics-platform-project.git
    cd Talentflow-Recruitment-Analytics-platform-project

2️ Configure MySQL Database-

    # application.properties
    spring.datasource.url=jdbc:mysql://localhost:3306/recruitment_management
    spring.datasource.username=your_mysql_username
    spring.datasource.password=your_mysql_password
    spring.jpa.hibernate.ddl-auto=update

3️ Run the Application

4️ Access API Endpoints-

    http://localhost:8080/api/candidates
    http://localhost:8080/api/jobs
    http://localhost:8080/api/interviews/schedule
    http://localhost:8080/api/resumes/upload{id}
    http://localhost:8080/api/reports/summary
    
📈 Future Improvements
•	✅ Add authentication and role-based access (Admin, Recruiter)
•	✅ Integrate PDF parsing for resume upload
•	✅ Export reports to Excel or PDF
•	✅ Connect to front-end (React/Angular)
•	✅ Integrate with LinkedIn APIs or external job boards
