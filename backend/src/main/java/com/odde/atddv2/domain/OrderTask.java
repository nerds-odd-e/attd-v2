package com.odde.atddv2.domain;

import com.odde.atddv2.config.TaskSwitch;
import com.odde.atddv2.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import static com.odde.atddv2.entity.Order.OrderStatus.done;

@Component
public class OrderTask {

    @Autowired
    TaskSwitch taskSwitch;

    @Autowired
    private OrderRepo orderRepo;

    @Scheduled(fixedDelayString = "${scheduled.order-task-in-msec}")
    public void completeDone() {
        taskSwitch.waitForExecute();
        orderRepo.findAll()
                .forEach(order -> {
                    order.setStatus(done);
                    orderRepo.save(order);
                });
    }
}
