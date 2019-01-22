package com.github.jvanheesch.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:another-properties-file.properties")
public class ApplicationConfiguration {
    @Value("${some.key.from.properties.file}")
    private String someString;

    @Value("${some.key.from.another.properties.file}")
    private String someStringFromAnotherPropertiesFile;

    @Bean
    public Object someBean(@Value("${some.key.from.properties.file}") String someBean) {
        return someBean;
    }
}
