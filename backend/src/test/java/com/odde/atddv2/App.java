package com.odde.atddv2;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import lombok.SneakyThrows;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URL;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

@Component
public class App {

    private AndroidDriver<AndroidElement> driver;

    @Value("${appium.udid}")
    private String udid;

    @SneakyThrows
    public void launch() {
        DesiredCapabilities caps = DesiredCapabilities.android();
        caps.setCapability("automationName", "UiAutomator2");
        caps.setCapability("udid", udid);
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("platformName", "Android");
        caps.setCapability("app", System.getProperty("user.dir") + "/../android/app/build/outputs/apk/debug/app-debug.apk");
        driver = new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), caps);
        driver.launchApp();
    }

    @PreDestroy
    public void close() {
        driver.quit();
    }

    public void inputTextByHint(String hint, String text) {
        waitElementByEditTextHint(hint).sendKeys(text);
    }

    public void clickByText(String text) {
        waitElementByText(text).click();
    }

    public void inputTextById(String id, String text) {
        waitElementById(id).sendKeys(text);
    }

    public void shouldHaveText(String text) {
        await().untilAsserted(() -> assertThat(driver.findElementsByAndroidUIAutomator(String.format("new UiSelector().text(\"%s\")", text))).isNotEmpty());
    }

    private AndroidElement waitElementByEditTextHint(String hint) {
        return await().ignoreExceptions().until(() -> driver.findElementsByAndroidUIAutomator("new UiSelector().className(\"android.widget.EditText\")")
                .stream().filter(e -> Objects.equals(e.getText(), hint))
                .findFirst().get(), Objects::nonNull);
    }

    private AndroidElement waitElementById(String id) {
        return await().ignoreExceptions().until(() -> driver.findElementById(id), Objects::nonNull);
    }

    private AndroidElement waitElementByText(String text) {
        return await().ignoreExceptions().until(() -> driver.findElementByAndroidUIAutomator(String.format("new UiSelector().text(\"%s\")", text)), Objects::nonNull);
    }
}
