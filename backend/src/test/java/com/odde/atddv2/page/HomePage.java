package com.odde.atddv2.page;

import com.odde.atddv2.Browser;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.openqa.selenium.By.xpath;

@Component
public class HomePage {

    @Autowired
    public WebDriver webDriver;

    @Autowired
    public Browser browser;

    public void open() {
        browser.launchPath("/");
    }

    public void login(String userName, String password) {
        webDriver.findElement(xpath("//*[@placeholder='userName']")).sendKeys(userName);
        webDriver.findElement(xpath("//*[@placeholder='password']")).sendKeys(password);
        webDriver.findElement(xpath("//*[@value='Login']")).click();
    }
}
