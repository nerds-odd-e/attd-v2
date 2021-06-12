package com.odde.atddv2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.odde.atddv2.entity.Order;
import com.odde.atddv2.entity.Order.OrderStatus;
import com.odde.atddv2.entity.OrderLine;
import com.odde.atddv2.repo.OrderRepo;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.zh_cn.并且;
import io.cucumber.java.zh_cn.当;
import io.cucumber.java.zh_cn.那么;
import lombok.SneakyThrows;
import org.mockserver.matchers.Times;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.HashMap;
import java.util.function.UnaryOperator;

import static com.odde.atddv2.entity.Order.OrderStatus.delivering;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class ApiSteps {
    @Autowired
    private MockServer mockServer;
    @Autowired
    private Api api;
    @Autowired
    private OrderRepo orderRepo;

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

    @SneakyThrows
    @并且("当前时间为{string}")
    public void 当前时间为(String time) {
        mockServer.getJson("/clock", UnaryOperator.identity(), String.format("\"%s\"", time));
    }

    @当("通过API发货订单{string}，快递单号为{string}")
    public void 通过api发货订单快递单号为(String order, String deliverNo) {
        api.post(String.format("orders/%s/deliver", order), new HashMap<String, String>() {{
            put("deliverNo", deliverNo);
        }});
    }

    @当("订单任务运行时")
    public void 订单任务运行时() {
        mockServer.getJson("/task", UnaryOperator.identity(), Times.exactly(2), "true");
    }

    @那么("订单{string}已发货，发货时间为{string}，快递单号为{string}")
    public void 订单已发货发货时间为快递单号为(String order, String time, String deliverNo) {
        assertThat(orderRepo.findByCode(order))
                .hasFieldOrPropertyWithValue("deliverNo", deliverNo)
                .hasFieldOrPropertyWithValue("status", delivering)
                .hasFieldOrPropertyWithValue("deliveredAt", Instant.parse(time));
    }

    @那么("订单{string}的状态为{string}")
    public void 订单的状态为(String order, String status) {
        await().untilAsserted(() -> assertThat(orderRepo.findByCode(order))
                .hasFieldOrPropertyWithValue("status", OrderStatus.valueOf(status)));
    }
}
