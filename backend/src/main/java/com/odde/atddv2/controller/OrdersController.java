package com.odde.atddv2.controller;

import com.odde.atddv2.api.BinstdApi;
import com.odde.atddv2.api.Logistics;
import com.odde.atddv2.entity.Order;
import com.odde.atddv2.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class OrdersController {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    BinstdApi binstdApi;

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
        Order order = orderRepo.findByCode(code);
        Logistics.Result logistics = binstdApi.queryExpress("822c629b7815e01f", "auto", order.getDeliverNo()).getResult();
        Order.OrderLogistics orderLogistics = new Order.OrderLogistics();
        orderLogistics.setDeliverNo(logistics.getNumber())
                .setCompanyCode(logistics.getType())
                .setCompanyName(logistics.getTypename())
                .setCompanyLogo(logistics.getLogo())
                .setDetails(logistics.getList())
                .setDeliveryStatus(logistics.getDeliverystatus() == 1 ? "在途中" : "")
                .setIsSigned(logistics.getIssign() == 0 ? "未签收" : "");
        order.setLogistics(orderLogistics);
        return order;
    }
}
