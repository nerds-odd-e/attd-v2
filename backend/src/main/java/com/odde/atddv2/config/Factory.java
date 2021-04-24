package com.odde.atddv2.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
@ConditionalOnMissingBean({Clock.class, TaskSwitch.class})
public class Factory {

    @Bean
    public Clock createClock() {
        return Clock.systemUTC();
    }

    @Bean
    public TaskSwitch createTaskSwitch() {
        return () -> {
        };
    }
}
