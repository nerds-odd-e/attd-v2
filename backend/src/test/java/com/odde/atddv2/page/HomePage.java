package com.odde.atddv2.page;

import com.odde.atddv2.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HomePage {

    @Autowired
    public Browser browser;

    public void open() {
        browser.launchByUrl("/");
    }

    public void login(String userName, String password) {
        browser.inputTextByPlaceholder("用户名", userName);
        browser.inputTextByPlaceholder("密码", password);
        browser.clickByText("登录");
    }
}
