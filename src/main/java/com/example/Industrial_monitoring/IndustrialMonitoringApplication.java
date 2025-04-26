package com.example.Industrial_monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class IndustrialMonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(IndustrialMonitoringApplication.class, args);
	}

}
