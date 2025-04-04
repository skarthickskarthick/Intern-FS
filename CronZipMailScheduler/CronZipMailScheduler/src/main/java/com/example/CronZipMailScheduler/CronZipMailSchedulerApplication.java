package com.example.CronZipMailScheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CronZipMailSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CronZipMailSchedulerApplication.class, args);
	}

}
