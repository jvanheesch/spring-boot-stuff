package com.github.jvanheesch.boot;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WicketApplication extends WebApplication {
    @Override
    public Class<? extends WebPage> getHomePage() {
        return HomePage.class;
    }

    @Override
    protected void init() {
        super.init();

        this.getComponentInstantiationListeners().add(new SpringComponentInjector(this));
    }

    @Bean
    public FilterRegistrationBean<?> someFilterRegistration() {
        FilterRegistrationBean<WicketFilter> registration = new FilterRegistrationBean<>();
        registration.setName("WicketApplication");
        registration.setFilter(new WicketFilter());
        registration.addInitParameter("applicationClassName", WicketApplication.class.getName());
        registration.addInitParameter(WicketFilter.FILTER_MAPPING_PARAM, "/*");
        registration.addUrlPatterns("/*");
        return registration;
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WicketApplication.class, args);
    }
}
