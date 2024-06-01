package com.oneclick.oneclick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude = SecurityAutoConfiguration.class)
public class OneclickApplication {

	public static void main(String[] args) {
		SpringApplication.run(OneclickApplication.class, args);
	}

}
