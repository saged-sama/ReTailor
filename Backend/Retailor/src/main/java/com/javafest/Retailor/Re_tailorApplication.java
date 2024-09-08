package com.javafest.Retailor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Re_tailorApplication {

	public static void main(String[] args) {
		SpringApplication.run(Re_tailorApplication.class, args);
	}

}
