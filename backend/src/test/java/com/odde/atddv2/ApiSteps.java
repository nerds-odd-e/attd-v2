package com.odde.atddv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odde.atddv2.entity.Order;
import com.odde.atddv2.entity.OrderLine;
import com.odde.atddv2.repo.OrderRepo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.zh_cn.并且;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import lombok.SneakyThrows;
import org.mockserver.client.MockServerClient;
import org.mockserver.matchers.Times;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.transaction.Transactional;
import java.net.URL;

import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

public class ApiSteps {
    @Autowired
    private Api api;

    @Autowired
    private OrderRepo orderRepo;
    private MockServerClient mockServerClient = createMockServerClient();
    @Value("${binstd-endpoint.key}")
    private String binstdAppKey;

    @那么("返回如下订单")
    public void 返回如下订单(String json) {
        api.responseShouldMatchJson(json);
    }

    @并且("存在订单{string}的订单项:")
    @Transactional
    public void 存在订单的订单项(String orderCode, DataTable table) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = orderRepo.findByCode(orderCode);
        table.asMaps().forEach(map -> order.getLines().add(objectMapper.convertValue(map, OrderLine.class).setOrder(order)));
        orderRepo.save(order);
    }

    @当("API查询订单{string}详情时")
    public void api查询订单详情时(String code) {
        api.get(String.format("orders/%s", code));
    }

    @Before(order = 0)
    public void resetMockServer() {
        mockServerClient.reset();
    }

    @并且("存在快递单{string}的物流信息如下")
    public void 存在快递单的物流信息如下(String deliverNo, String json) {
        mockServerClient.when(
                request().withMethod("GET")
                        .withQueryStringParameter("appkey", binstdAppKey)
                        .withQueryStringParameter("type", "auto")
                        .withQueryStringParameter("number", deliverNo)
                        .withPath("/express/query"),
                Times.unlimited()
        ).respond(response()
                .withStatusCode(200)
                .withHeader(CONTENT_TYPE, "application/json")
                .withBody(json));
    }

    @当("API查询订单时")
    public void api查询订单时() {
        api.get("orders");
    }

    @SneakyThrows
    private MockServerClient createMockServerClient() {
        URL url = new URL("http://mock-server.tool.net:9081");
        return new MockServerClient(url.getHost(), url.getPort()) {

            @Override
            public void close() {
            }
        };
    }
}
