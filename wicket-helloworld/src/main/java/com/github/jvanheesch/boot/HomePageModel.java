package com.github.jvanheesch.boot;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePageModel extends LoadableDetachableModel<String> {
    private static final long serialVersionUID = 3671591196001641047L;

    @SpringBean
    private SingletonServiceWithSmallerScopedDependencies service;

    public HomePageModel() {
        Injector.get().inject(this);
    }

    @Override
    protected String load() {
        return service.getString();
    }
}
