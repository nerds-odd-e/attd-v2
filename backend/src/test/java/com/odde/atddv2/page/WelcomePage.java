package com.odde.atddv2.page;

import com.odde.atddv2.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WelcomePage {
    @Autowired
    Browser browser;

    public void goToOrders() {
        browser.clickByText("订单");
    }
}
