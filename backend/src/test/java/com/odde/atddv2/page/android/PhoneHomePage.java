package com.odde.atddv2.page.android;

import com.odde.atddv2.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneHomePage {

    @Autowired
    App app;

    public void open() {
        app.launch();
    }

    public void login(String username, String password) {

    }
}
