package com.github.jvanheesch.boot;

import com.github.jvanheesch.boot.beans.SpringInterface;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.spring.injection.annot.SpringBean;

public class HomePage extends WebPage {
    private static final long serialVersionUID = -4628714388032191363L;

    @SpringBean
    private SpringInterface springInterface;

    public HomePage() {
        Injector.get().inject(this);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        this.add(new Label("message", this.springInterface.getString()));
    }
}
