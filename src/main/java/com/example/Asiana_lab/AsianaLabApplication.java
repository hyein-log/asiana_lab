package com.example.Asiana_lab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example.Asiana_lab"})
public class AsianaLabApplication {

	public static void main(String[] args) {
		SpringApplication.run(AsianaLabApplication.class, args);
	}

}
