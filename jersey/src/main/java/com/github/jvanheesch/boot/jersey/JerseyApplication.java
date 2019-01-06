package com.github.jvanheesch.boot.jersey;

import com.github.jvanheesch.servlet.InfoFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JerseyApplication {
    public static void main(String[] args) {
        SpringApplication.run(JerseyApplication.class, args);
    }

    @Bean
    public FilterRegistrationBean<InfoFilter> infoFilter() {
        FilterRegistrationBean<InfoFilter> filterRegistrationBean
                = new FilterRegistrationBean<>();

        filterRegistrationBean.setFilter(new InfoFilter());
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }
}
