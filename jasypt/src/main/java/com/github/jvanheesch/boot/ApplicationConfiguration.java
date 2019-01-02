package com.github.jvanheesch.boot;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.AbstractEnvironment;

/**
 * See https://www.baeldung.com/spring-boot-jasypt
 */
@Configuration
public class ApplicationConfiguration {
    @Bean(name = "encryptorBean")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword("password");
        config.setAlgorithm("PBEWithMD5AndDES");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

    @Bean
    @DependsOn("encryptorBean")
    public Object testJasypt(AbstractEnvironment environment, @Value("${encryptedv3.property}") String decryptedValue) {
        System.out.println(environment.getProperty("encryptedv3.property"));
        System.out.println(decryptedValue);
        System.out.println(environment.getPropertySources());
        return null;
    }
}
