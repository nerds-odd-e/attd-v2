package com.odde.atddv2.page;

import com.odde.atddv2.Browser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class OrderPage {

    @Autowired
    public Browser browser;

    public void addOrder(Map<String, String> order) {
        browser.clickByText("录入订单");
        order.forEach((placeholder, text) -> {
            if (!placeholder.equals("状态")) {
                browser.inputTextByPlaceholder(placeholder, text);
            }
        });
        browser.selectTextByPlaceholder("状态", order.get("状态"));
        browser.clickByText("提交");
        browser.shouldNotHaveText("提交");
    }
}
