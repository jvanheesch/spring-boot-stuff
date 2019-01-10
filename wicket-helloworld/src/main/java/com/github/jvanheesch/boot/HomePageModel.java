package com.github.jvanheesch.boot;

import org.apache.wicket.injection.Injector;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePageModel extends LoadableDetachableModel<String> {
    private static final long serialVersionUID = 3671591196001641047L;

    @SpringBean(name = "requestScopeObject")
    private Stringable requestScopeObject;
    @SpringBean(name = "sessionScopeObject")
    private Stringable sessionScopeObject;
    @SpringBean(name = "singletonScopeObject")
    private Stringable singletonScopeObject;

    public HomePageModel() {
        Injector.get().inject(this);
    }

    @Override
    protected String load() {
        return requestScopeObject.asString() + sessionScopeObject.asString() + singletonScopeObject.asString();
    }
}
