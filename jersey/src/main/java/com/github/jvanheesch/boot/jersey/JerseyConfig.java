package com.github.jvanheesch.boot.jersey;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import javax.ws.rs.ApplicationPath;

// https://stackoverflow.com/a/29670751
// both localhost:8080/jersey/hello/world and localhost:8080/actuator/health now work.
@ApplicationPath("/jersey")
@Component
public class JerseyConfig extends ResourceConfig {
    public JerseyConfig() {
        registerEndpoints();
    }

    private void registerEndpoints() {
        register(HelloWorldEndpoint.class);
        register(JaxRsEndpoint.class);
        register(JaxRsConsumesIngoredEndpoint.class);
        register(JaxRsConsumesNotIngoredEndpoint.class);
    }
}