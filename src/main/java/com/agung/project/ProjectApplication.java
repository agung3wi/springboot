package com.agung.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages={
	"com.agung.project", 
	"com.agung.project.dao", 
	"com.agung.project.service", 
	"com.agung.project.model"
})

public class ProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

}
