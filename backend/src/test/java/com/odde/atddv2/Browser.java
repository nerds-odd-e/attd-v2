package com.odde.atddv2;

import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;

@Component
public class Browser {

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private WebDriver webDriver;

    public void launchPath(String path) {
        webDriver.get("http://localhost:" + serverProperties.getPort() + path);
    }
}
