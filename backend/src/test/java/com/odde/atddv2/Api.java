package com.odde.atddv2;

import com.odde.atddv2.entity.User;
import com.odde.atddv2.repo.UserRepo;
import io.cucumber.java.Before;
import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class Api {
    private final RestTemplate restTemplate = new RestTemplate();
    private String response, token;
    @Autowired
    private ServerProperties serverProperties;
    @Autowired
    private UserRepo userRepo;

    @Before("@api-login")
    public void apiLogin() {
        User defaultUser = new User().setUserName("j").setPassword("j");
        userRepo.save(defaultUser);
        token = restTemplate.postForEntity(makeUri("/users/login"), defaultUser, User.class)
                .getHeaders().get("token").get(0);
    }

    public void get(String path) {
        response = restTemplate.exchange(RequestEntity.get(makeUri("/api/" + path))
                .header("Accept", "application/json").header("token", token)
                .build(), String.class).getBody();
    }

    @SneakyThrows
    private URI makeUri(String path) {
        return URI.create(String.format("http://127.0.0.1:%s%s", serverProperties.getPort(), path));
    }

    @SneakyThrows
    public void responseShouldMatchJson(String json) {
        JSONAssert.assertEquals(json, response, JSONCompareMode.NON_EXTENSIBLE);
    }
}