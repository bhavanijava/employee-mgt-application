package com.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@EnableWebMvc
@SpringBootApplication
@OpenAPIDefinition(info=@Info(description = "Employee Application"))
@EnableScheduling
@EnableJpaAuditing
public class EmployeeMgtApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeMgtApplication.class, args);
	}

}
