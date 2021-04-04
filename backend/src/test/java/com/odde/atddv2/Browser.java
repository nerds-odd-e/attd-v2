package com.odde.atddv2;

import lombok.SneakyThrows;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.openqa.selenium.By.xpath;

@Component
public class Browser {

    private final WebDriver webDriver = createWebDriver();
    @Autowired
    private ServerProperties serverProperties;

    public void launchByUrl(String path) {
        webDriver.get("http://localhost:" + serverProperties.getPort() + path);
    }

    public void inputTextByPlaceholder(String placeholder, String text) {
        waitElement("//*[@placeholder='" + placeholder + "']").sendKeys(text);
    }

    public void clickByText(String text) {
        waitElement(String.format("//*[@value='%s' or text()='%s']", text, text)).click();
    }

    public void shouldHaveText(String text) {
        await().untilAsserted(() -> assertThat(webDriver.findElements(xpath("//*[text()='" + text + "']"))).isNotEmpty());
    }

    @PreDestroy
    public void close() {
        webDriver.close();
    }

    public WebDriver createWebDriver() {
        System.setProperty("webdriver.chrome.driver", getChromeDriverBinaryPath());
        return new ChromeDriver();
    }

    @SneakyThrows
    private String getChromeDriverBinaryPath() {
        try (Stream<Path> walkStream = Files.walk(Paths.get(System.getProperty("user.home"), ".gradle", "webdriver", "chromedriver"))) {
            return walkStream
                    .filter(this::isChromeDriverBinary)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("can't find chrome driver binary"))
                    .toAbsolutePath().toString();
        }
    }

    private boolean isChromeDriverBinary(Path p) {
        File file = p.toFile();
        return file.isFile() && (file.getPath().endsWith("chromedriver") || file.getPath().endsWith("chromedriver.exe"));
    }

    private WebElement waitElement(String xpathExpression) {
        return await().until(() -> webDriver.findElement(xpath(xpathExpression)), Objects::nonNull);
    }
}
