package com.InsRem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class InsuranceReminderApplication {

    public static void main(String[] args) {
        SpringApplication.run(InsuranceReminderApplication.class, args);
        System.out.println("Started!");
    }

}
