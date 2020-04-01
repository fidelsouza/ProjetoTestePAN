package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("com.example.controller")
@ComponentScan("com.example.services")
@EntityScan("com.example.model")  
@EnableJpaRepositories("com.example.repository")
public class TestePanApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestePanApplication.class, args);
	}

}
