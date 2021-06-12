package com.odde.atddv2.domain;

import com.odde.atddv2.config.TaskSwitch;
import com.odde.atddv2.repo.OrderRepo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Clock;

import static com.odde.atddv2.entity.Order.OrderStatus.done;

@Component
public class OrderTask {

    @Autowired
    @Qualifier("completeDoneTaskSwitch")
    TaskSwitch taskSwitch;

    @Autowired
    @Qualifier("anotherTaskSwitch")
    TaskSwitch anotherTaskSwitch;

    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private Clock clock;

    @SneakyThrows
    @Scheduled(fixedDelayString = "${scheduled.order-task-in-msec}")
    public void completeDone() {
        taskSwitch.waitForExecute();
        System.out.println("\"first task\" = " + "first task");
        orderRepo.findAll().stream()
                .filter(order -> order.isDone(clock))
                .forEach(order -> orderRepo.save(order.setStatus(done)));
    }

    @SneakyThrows
    @Scheduled(fixedDelayString = "${scheduled.order-task-in-msec}")
    public void another() {
        anotherTaskSwitch.waitForExecute();
        System.out.println("\"another task\" = " + "another task");
    }
}
