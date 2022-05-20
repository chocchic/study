package io.github.chocchic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// JPA 관련된 작업을 감시
@EnableJpaAuditing
@SpringBootApplication
public class BootIoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootIoApplication.class, args);
	}

}
