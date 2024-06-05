package com.springbootprojects.booknetworkapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class BooknetworkapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooknetworkapiApplication.class, args);
	}

}
