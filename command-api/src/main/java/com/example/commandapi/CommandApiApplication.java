package com.example.commandapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.commandapi.model"})
public class CommandApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommandApiApplication.class, args);
    }

}
