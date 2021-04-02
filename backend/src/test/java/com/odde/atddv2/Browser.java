package com.odde.atddv2;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

@Component
public class Browser {

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private WebDriver webDriver;

    public void launchPath(String path) {
        webDriver.get("http://localhost:" + serverProperties.getPort() + path);
    }

    public void inputTextByPlaceholder(String placeholder, String text) {
        webDriver.findElement(xpath("//*[@placeholder='" + placeholder + "']")).sendKeys(text);
    }

    public void clickByText(String text) {
        webDriver.findElement(xpath("//*[@value='" + text + "']")).click();
    }

    public void shouldHaveText(String text) {
        await().untilAsserted(() -> assertThat(webDriver.findElements(xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }
}
