package com.example.eduwithbe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class EduwithBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduwithBeApplication.class, args);
    }

}
