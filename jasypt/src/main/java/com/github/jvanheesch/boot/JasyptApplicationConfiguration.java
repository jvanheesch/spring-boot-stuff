package com.github.jvanheesch.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.AbstractEnvironment;

/**
 * See https://www.baeldung.com/spring-boot-jasypt
 */
@Configuration
//@EncryptablePropertySource and @PropertySource seem to behave the same.
@PropertySource("classpath:another-properties-file.properties")
public class JasyptApplicationConfiguration {
    @Bean
    public Object testJasypt(AbstractEnvironment environment, @Value("${some.key.from.another.properties.file}") String decryptedValue) {
        System.out.println(environment.getProperty("encryptedv3.property"));
        System.out.println(decryptedValue);
        System.out.println(environment.getPropertySources());
        return null;
    }
}
