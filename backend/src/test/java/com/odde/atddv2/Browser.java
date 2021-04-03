package com.odde.atddv2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

@Component
public class Browser {

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private WebDriver webDriver;

    public void launchByUrl(String path) {
        webDriver.get("http://localhost:" + serverProperties.getPort() + path);
    }

    public void inputTextByPlaceholder(String placeholder, String text) {
        waitElement("//*[@placeholder='" + placeholder + "']").sendKeys(text);
    }

    public void clickByText(String text) {
        waitElement("//*[@value='" + text + "']").click();
    }

    public void shouldHaveText(String text) {
        await().untilAsserted(() -> assertThat(webDriver.findElements(xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }

    @PreDestroy
    public void close() {
        webDriver.close();
    }

    private WebElement waitElement(String xpathExpression) {
        return await().until(() -> webDriver.findElement(xpath(xpathExpression)), Objects::nonNull);
    }
}
