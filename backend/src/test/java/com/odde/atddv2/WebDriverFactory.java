package com.odde.atddv2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebDriverFactory {

    @Bean
    public WebDriver createWebDriver() {
        return new ChromeDriver();
    }
}
