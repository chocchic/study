package com.chocchic.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class ToDoBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoBackEndApplication.class, args);
	}

}
