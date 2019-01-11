package com.github.jvanheesch.boot;

import org.apache.wicket.Session;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.protocol.http.WicketFilter;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.util.function.Supplier;

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

    @Bean
    @RequestScope
    public Supplier<String> requestScopeString() {
        System.out.println("WicketApplication.requestScopeString");

        Object o = new Object();
        return o::toString;
    }

    @Bean
    @SessionScope
    public Supplier<String> sessionScopeString() {
        System.out.println("WicketApplication.sessionScopeString");

        Object o = new Object();
        return o::toString;
    }

    @Bean
    @SessionScope
    public Supplier<String> sessionScopeString2() {
        System.out.println("WicketApplication.sessionScopeString2");

        return () -> "sessionScopeString2";
    }

    @Bean
    public Supplier<String> singletonScopeString() {
        System.out.println("WicketApplication.singletonScopeString");

        Object o = new Object();
        return o::toString;
    }

    @Override
    public Session newSession(Request request, Response response) {
        return new MyWebSession(request);
    }

    private static class MyWebSession extends WebSession {
        private static final long serialVersionUID = -1776890521228839052L;

        @SpringBean(name = "sessionScopeString2")
        private Supplier<String> sessionScopeString2;

        public MyWebSession(Request request) {
            super(request);

            Injector.get().inject(this);

            System.out.println("sessionScopeString2: " + sessionScopeString2.get());
        }
    }

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WicketApplication.class, args);
    }
}
