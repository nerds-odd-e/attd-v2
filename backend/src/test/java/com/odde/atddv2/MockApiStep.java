package com.odde.atddv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.leeonky.jfactory.JFactory;
import com.github.leeonky.jfactory.cucumber.Table;
import com.odde.atddv2.jfactory.api.LogisticsResponse;
import io.cucumber.java.zh_cn.并且;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MockApiStep {
    @Value("${binstd-endpoint.key}")
    private String binstdAppKey;

    @Autowired
    private MockServer mockServer;

    @并且("存在快递单{string}的物流信息如下:")
    @SneakyThrows
    public void 存在快递单的物流信息如下(String deliverNo, Table table) {
        JFactory jFactory = new JFactory();
        LogisticsResponse logisticsResponse = jFactory.type(LogisticsResponse.class).properties(table.flatSub()[0]).create();

        mockServer.getJson("/express/query", (request) -> request.withQueryStringParameter("appkey", binstdAppKey)
                .withQueryStringParameter("type", "auto")
                .withQueryStringParameter("number", deliverNo), new ObjectMapper().writeValueAsString(logisticsResponse));
    }
}
