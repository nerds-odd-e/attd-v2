package com.odde.atddv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

@Configuration
public class ClockFactory {

    @Bean
    public Clock createClock() {
        return Clock.systemUTC();
    }
}
