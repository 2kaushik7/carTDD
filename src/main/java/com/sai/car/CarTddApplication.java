package com.sai.car;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = { "com.sai.car.model" })
@SpringBootApplication
public class CarTddApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarTddApplication.class, args);
	}

}
