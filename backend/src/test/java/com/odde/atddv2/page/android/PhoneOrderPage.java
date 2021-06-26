package com.odde.atddv2.page.android;

import com.odde.atddv2.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PhoneOrderPage {

    @Autowired
    App app;

    public void shouldDisplay() {
        app.shouldHaveText("Order");
    }
}
