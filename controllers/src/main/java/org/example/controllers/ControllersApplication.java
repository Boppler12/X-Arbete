package org.example.controllers;

import org.example.auth.Service.JWS_Token;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.example.bff.JwtService;

@SpringBootApplication
public class ControllersApplication {

    public static void main(String[] args) {
        SpringApplication.run(ControllersApplication.class, args);
    }

}
