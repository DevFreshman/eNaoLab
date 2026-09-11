package org.com.lab;

import org.example.javaframework.annotation.EnableFrameworkSecurity;
import org.example.javaframework.annotation.EnableFrameworkSession;
import org.example.javaframework.configuration.SecurityConfig;
import org.example.javaframework.infra.security.JwtProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableFrameworkSecurity
@EnableFrameworkSession
public class LabApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabApplication.class, args);
    }

}
