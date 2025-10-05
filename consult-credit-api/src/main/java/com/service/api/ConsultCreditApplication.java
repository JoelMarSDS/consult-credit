package com.service.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
		"com.service.api",
		"com.service.domain",
		"com.service.logging"
})
@EntityScan("com.service.domain.model")
@EnableJpaRepositories("com.service.domain.repository")
public class ConsultCreditApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConsultCreditApplication.class, args);
	}
}
