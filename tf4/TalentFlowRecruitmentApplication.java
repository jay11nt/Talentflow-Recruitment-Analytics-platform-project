package com.tr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TalentFlowRecruitmentApplication {

	public static void main(String[] args) {
		SpringApplication.run(TalentFlowRecruitmentApplication.class, args);
	}

}
