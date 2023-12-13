package com.example.librarycosmartproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class LibraryCosmartProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibraryCosmartProjectApplication.class, args);
    }

}
