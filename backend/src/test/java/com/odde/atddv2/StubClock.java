package com.odde.atddv2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.Clock;
import java.time.Instant;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Configuration
public class StubClock {

    @Bean
    @Primary
    public Clock createStubClock() {
        Clock mock = mock(Clock.class);
        when(mock.instant()).thenAnswer(arg -> Instant.now());
        return mock;
    }
}
