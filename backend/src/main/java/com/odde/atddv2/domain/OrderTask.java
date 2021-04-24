package com.odde.atddv2.domain;

import com.odde.atddv2.config.TaskSwitch;
import com.odde.atddv2.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;

import static com.odde.atddv2.entity.Order.OrderStatus.done;

@Component
public class OrderTask {

    @Autowired
    TaskSwitch taskSwitch;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private Clock clock;

    @Scheduled(fixedDelayString = "${scheduled.order-task-in-msec}")
    public void completeDone() {
        taskSwitch.waitForExecute();
        orderRepo.findAll().stream()
                .filter(order -> order.isDone(clock))
                .forEach(order -> orderRepo.save(order.setStatus(done)));
    }
}
