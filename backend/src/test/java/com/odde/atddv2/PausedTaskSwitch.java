package com.odde.atddv2;

import com.odde.atddv2.config.TaskSwitch;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;

@Component
@Primary
public class PausedTaskSwitch implements TaskSwitch {

    private Semaphore semaphore = new Semaphore(0);

    public void go() {
        semaphore.release();
    }

    @SneakyThrows
    @Override
    public void waitForExecute() {
        semaphore.acquire();
    }
}
