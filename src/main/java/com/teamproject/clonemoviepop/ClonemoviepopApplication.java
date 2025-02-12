package com.teamproject.clonemoviepop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing //Auditing 기능을 쓰기 위해 필수
@SpringBootApplication
public class ClonemoviepopApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClonemoviepopApplication.class, args);
	}

}
