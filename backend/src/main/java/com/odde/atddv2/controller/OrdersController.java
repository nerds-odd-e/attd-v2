package com.odde.atddv2.controller;

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

    @GetMapping
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @PostMapping
    public Order addOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }
}
