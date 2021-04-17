package com.odde.atddv2;

import com.odde.atddv2.entity.User;
import com.odde.atddv2.repo.UserRepo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import lombok.SneakyThrows;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class ApiSteps {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private ServerProperties serverProperties;

    private String response;

    @Autowired
    private ApplicationSteps applicationSteps;

    @当("API查询订单时")
    public void api查询订单时() {
        applicationSteps   .存在用户名为和密码为的用户("j", "j");
        ResponseEntity<User> responseEntity = restTemplate.postForEntity(String.format("http://127.0.0.1:%s/users/login", serverProperties.getPort()), new User().setUserName("j").setPassword("j"), User.class);
        String token = responseEntity.getHeaders().get("token").get(0);

        RequestEntity.HeadersBuilder<?> builder = RequestEntity
                .get(URI.create(String.format("http://127.0.0.1:%s/api/orders", serverProperties.getPort())));
        builder.header("Accept", "application/json")
                .header("token", token);

        response = restTemplate.exchange(builder.build(), String.class).getBody();
    }

    @SneakyThrows
    @那么("返回如下订单")
    public void 返回如下订单(String json) {
        JSONAssert.assertEquals(json, response, false);
    }
}
