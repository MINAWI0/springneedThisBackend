package com.example.springneedthisbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class SpringneedThisBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringneedThisBackendApplication.class, args);
    }

}
