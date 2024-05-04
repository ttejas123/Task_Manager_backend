package com.tejas.googleauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoogleauthApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoogleauthApplication.class, args);
	}

}
