package com.odde.atddv2;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

@Component
public class Browser {

    private final WebDriver webDriver = createWebDriver();
    @Autowired
    private ServerProperties serverProperties;

    @Value("${host.name:host.docker.internal}")
    private String hostName;

    @Value("${host.port:}")
    private String hostPort;

    public void launchByUrl(String path) {
        webDriver.get(String.format("http://%s:%d%s", hostName, getPort(), path));
    }

    public void inputTextByPlaceholder(String placeholder, String text) {
        waitElement("//*[@placeholder='" + placeholder + "']").sendKeys(text);
    }

    public void clickByText(String text) {
        waitElement(String.format("//*[normalize-space(@value)='%s' or normalize-space(text())='%s']", text, text)).click();
    }

    public void shouldHaveText(String text) {
        await().untilAsserted(() -> assertThat(webDriver.findElements(xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }

    @PreDestroy
    public void close() {
        webDriver.quit();
    }

    @SneakyThrows
    public WebDriver createWebDriver() {
        return new RemoteWebDriver(new URL("http://web-driver.tool.net:4444"), DesiredCapabilities.chrome());
    }

    public void selectTextByPlaceholder(String placeholder, String text) {
        waitElement(String.format("//*[normalize-space(@placeholder)='%s']", placeholder)).click();
        clickByText(text);
    }

    public void shouldNotHaveText(String text) {
        await().untilAsserted(() -> assertThat(webDriver.findElements(xpath("//*[text()='" + text + "']"))).isEmpty());
    }

    private Integer getPort() {
        return !hostPort.isEmpty() ? Integer.valueOf(hostPort) : serverProperties.getPort();
    }

    private WebElement waitElement(String xpathExpression) {
        return await().until(() -> webDriver.findElement(xpath(xpathExpression)), Objects::nonNull);
    }
}
