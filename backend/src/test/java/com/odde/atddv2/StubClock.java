package com.odde.atddv2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;

import static org.mockito.Mockito.mock;

@Configuration
public class StubClock {

    @Bean
    public Clock createClock() {
        return mock(Clock.class);
    }
}
