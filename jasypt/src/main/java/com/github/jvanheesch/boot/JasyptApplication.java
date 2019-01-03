package com.github.jvanheesch.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(JasyptApplicationConfiguration.class)
public class JasyptApplication {
    public static void main(String[] args) {
//        setSpringProfiles("local");
        setSpringProfiles("dev");
        SpringApplication.run(JasyptApplication.class, args);
    }

    private static void setSpringProfiles(String... profiles) {
        System.setProperty("spring.profiles.active", String.join(",", profiles));
    }
}
