package com.spring.taskmanager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskManagerApplication {

	public static void main(String[] args) {
		System.out.println("Swagger ui available at: http://localhost:8080/swagger-ui.html");
		SpringApplication.run(TaskManagerApplication.class, args);
	}

}
