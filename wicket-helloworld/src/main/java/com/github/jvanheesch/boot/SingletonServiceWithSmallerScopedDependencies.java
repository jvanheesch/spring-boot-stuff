package com.github.jvanheesch.boot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

// TODO_JORIS: https://stackoverflow.com/a/15230741/1939921
@RequestScope
@Component
public class SingletonServiceWithSmallerScopedDependencies {

    @Autowired
    @Qualifier(value = "requestScopeObject")
    private Stringable requestScopeObject;
    @Autowired
    @Qualifier(value = "sessionScopeObject")
    private Stringable sessionScopeObject;
    @Autowired
    @Qualifier(value = "singletonScopeObject")
    private Stringable singletonScopeObject;

    public String getString() {
        return requestScopeObject.asString() + sessionScopeObject.asString() + singletonScopeObject.asString();
    }
}
