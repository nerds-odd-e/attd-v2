package com.odde.atddv2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;

import static org.mockito.Mockito.mock;

@Configuration
public class StubClock {

    @Bean
    @Primary
    public Clock createClock() {
        return mock(Clock.class);
    }
}
