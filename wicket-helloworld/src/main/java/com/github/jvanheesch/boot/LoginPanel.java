package com.github.jvanheesch.boot;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

public class LoginPanel extends Panel {
    private static final long serialVersionUID = -77100360960608656L;

    public LoginPanel(String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        IModel<String> usernameModel = new Model<>();
        IModel<String> passwordModel = new Model<>();

        Form<?> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {
                WicketApplication.MyWebSession.get().signIn(usernameModel.getObject(), passwordModel.getObject());
            }
        };

        this.add(form);

        form.add(new TextField<>("username", usernameModel));
        form.add(new TextField<>("password", passwordModel));
    }
}
