package com.github.jvanheesch.boot.autoconfigure;

import com.github.jvanheesch.boot.autoconfigure.ProfileSpecificAutoConfiguration.DevConfiguration;
import com.github.jvanheesch.boot.autoconfigure.ProfileSpecificAutoConfiguration.LocalConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Import({LocalConfiguration.class, DevConfiguration.class})
@Configuration
public class ProfileSpecificAutoConfiguration {
    @Profile("local")
    @PropertySource("classpath:local.properties")
    @Configuration
    static class LocalConfiguration {

    }

    @Profile("dev")
    @PropertySource("classpath:dev.properties")
    @Configuration
    static class DevConfiguration {

    }
}
