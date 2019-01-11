package com.github.jvanheesch.boot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MyProperties {
    @Value("${some.key}")
    private String someKey;

    public String getSomeKey() {
        return this.someKey;
    }
}
