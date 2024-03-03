package com.odde.atddv2.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.odde.atddv2.api.BinstdApi;
import com.odde.atddv2.entity.Order;
import com.odde.atddv2.repo.OrderRepo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
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

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    private RedisTemplate<String, Order.OrderTransaction> redisUserTemplate() {
        RedisTemplate<String, Order.OrderTransaction> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer(Order.OrderTransaction.class));
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    @GetMapping
    @JsonView(GetAllOrders.class)
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @PostMapping
    @Operation(hidden = true)
    public Order addOrder(@RequestBody Order order) {
        return orderRepo.save(order);
    }

    @GetMapping("/{code}")
    @JsonView(GetOrder.class)
    public Order getOrder(@PathVariable String code) {
        Order order = orderRepo.findByCode(code);
        order.setTransaction(redisUserTemplate().opsForValue().get("TRANSACTION_" + order.getCode()));
        if (order.getDeliverNo() != null) {
            return order.populateLogistics(binstdApi.queryExpress(binstdAppKey, order.getDeliverNo()).getResult());
        } else {
            return order;
        }
    }

    @PostMapping("{code}/deliver")
    public Order deliver(@PathVariable String code, @RequestBody @JsonView(DeliverOrder.class) Order order) {
        return orderRepo.save(orderRepo.findByCode(code)
                .setDeliverNo(order.getDeliverNo())
                .setDeliveredAt(clock.instant())
                .setStatus(Order.OrderStatus.delivering));
    }

}
