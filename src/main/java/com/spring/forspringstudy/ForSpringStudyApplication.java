package com.spring.forspringstudy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing		//JPA Auditing기능 활성화 (BaseTimeEntity의 auditing 기능)
@SpringBootApplication
public class ForSpringStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForSpringStudyApplication.class, args);
	}

}
