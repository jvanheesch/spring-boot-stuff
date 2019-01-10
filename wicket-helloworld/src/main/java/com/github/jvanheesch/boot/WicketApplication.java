package com.github.jvanheesch.boot;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

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

    @Bean(name = "requestScopeObject")
    @RequestScope
    public Stringable requestScopeObject() {
        System.out.println("WicketApplication.requestScopeObject");

        Object o = new Object();
        return o::toString;
    }

    @Bean(name = "sessionScopeObject")
    @SessionScope
    public Stringable sessionScopeObject() {
        System.out.println("WicketApplication.sessionScopeObject");

        Object o = new Object();
        return o::toString;
    }

    @Bean(name = "singletonScopeObject")
    public Stringable singletonScopeObject() {
        System.out.println("WicketApplication.singletonScopeObject");

        Object o = new Object();
        return o::toString;
    }

    @Override
    public Session newSession(Request request, Response response) {
        System.out.println("WicketApplication.newSession");

        return super.newSession(request, response);
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WicketApplication.class, args);
    }
}
