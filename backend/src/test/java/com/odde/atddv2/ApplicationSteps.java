package com.odde.atddv2;

import io.cucumber.java.After;
import io.cucumber.java.zh_cn.假如;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {SpringVueApplication.class}, loader = SpringBootContextLoader.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@CucumberContextConfiguration
public class ApplicationSteps {

    private final WebDriver webDriver = new ChromeDriver();

    @假如("存在用户名为{string}和密码为{string}的用户")
    public void 存在用户名为和密码为的用户(String userName, String password) {
    }

    @SneakyThrows
    @当("以用户名为{string}和密码为{string}登录时")
    public void 以用户名为和密码为登录时(String userName, String password) {
        webDriver.get("http://localhost:8080");
        webDriver.findElement(By.name("userName")).sendKeys(userName);
        webDriver.findElement(By.name("password")).sendKeys(password);
        webDriver.findElement(By.xpath("//*[@value='Login']")).click();
    }

    @那么("登录成功")
    public void 登录成功() {
    }

    @After
    public void close() {
        webDriver.close();
    }
}
