package com.odde.atddv2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;

@Configuration
@Profile("dev")
public class ClockFactory {

    @Bean
    public Clock createClock() {
        return Clock.systemUTC();
    }
}
