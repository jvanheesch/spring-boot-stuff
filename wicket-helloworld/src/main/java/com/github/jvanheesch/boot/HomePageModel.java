package com.github.jvanheesch.boot;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import java.util.function.Supplier;

public class HomePageModel extends LoadableDetachableModel<String> {
    private static final long serialVersionUID = 3671591196001641047L;

    @SpringBean(name = "requestScopeString")
    private Supplier<String> requestScopeString;
    @SpringBean(name = "sessionScopeString")
    private Supplier<String> sessionScopeString;
    @SpringBean(name = "singletonScopeString")
    private Supplier<String> singletonScopeString;

    public HomePageModel() {
        Injector.get().inject(this);
    }

    @Override
    protected String load() {
        return requestScopeString.get() + sessionScopeString.get() + singletonScopeString.get();
    }
}
