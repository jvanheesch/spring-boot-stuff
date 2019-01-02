package com.github.jvanheesch.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {
    @Value("${some.key.from.properties.file}")
    private String someString;

    @Bean
    public Object someBean(@Value("${some.key.from.properties.file}") String someBean) {
        return someBean;
    }
}
