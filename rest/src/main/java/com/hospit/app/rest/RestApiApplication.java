package com.hospit.app.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * @author: Artem Korzhan
 * @version: 1.0
 * The {@code RestApiApplication} class is the main entry point for the Spring Boot application.
 * It is annotated with {@link SpringBootApplication} to enable Spring Boot features.
 * The {@link SecurityAutoConfiguration} is excluded to disable the default Spring Security configuration.
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class RestApiApplication {

	/**
	 * The main method that starts the Spring Boot application.
	 *
	 * @param args Command line arguments passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(RestApiApplication.class, args);
	}
}