package com.odde.atddv2;

import com.odde.atddv2.page.android.PhoneHomePage;
import com.odde.atddv2.page.android.PhoneOrderPage;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import org.springframework.beans.factory.annotation.Autowired;

public class AndroidSteps {

    @Autowired
    PhoneHomePage phoneHomePage;

    @Autowired
    PhoneOrderPage phoneOrderPage;

    @当("以用户名为{string}和密码为{string}手机登录时")
    public void 以用户名为和密码为手机登录时(String username, String password) {
        phoneHomePage.open();
        phoneHomePage.login(username, password);
    }

    @那么("手机登录成功")
    public void 手机登录成功() {
        phoneOrderPage.shouldDisplay();
    }
}
