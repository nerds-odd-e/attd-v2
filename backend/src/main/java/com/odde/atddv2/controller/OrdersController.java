package com.odde.atddv2.controller;

import com.odde.atddv2.api.BinstdApi;
import com.odde.atddv2.entity.Order;
import com.odde.atddv2.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.time.Clock;
import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrdersController {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    BinstdApi binstdApi;

    @Value("${binstd-endpoint.key}")
    private String binstdAppKey;

    @Autowired
    private Clock clock;

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }

    @GetMapping("/{code}")
    public Order getOrder(@PathVariable String code) {
        return orderRepo.findByCode(code)
                .populateLogistics(binstdApi.queryExpress(binstdAppKey, orderRepo.findByCode(code).getDeliverNo()).getResult());
    }

    @PostMapping("{code}/deliver")
    public Order deliver(@PathVariable String code, @RequestBody Order order) {
        return orderRepo.save(orderRepo.findByCode(code)
                .setDeliverNo(order.getDeliverNo())
                .setDeliveredAt(clock.instant())
                .setStatus(Order.OrderStatus.delivering));
    }
}
