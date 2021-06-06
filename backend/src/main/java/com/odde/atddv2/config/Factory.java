package com.odde.atddv2.config;

import com.odde.atddv2.api.ClockApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

@Configuration
@ConditionalOnMissingBean({Clock.class, TaskSwitch.class})
public class Factory {

    @Autowired
    ClockApi clockApi;

    @Bean
    @Profile("!standalone-dev")
    public Clock createClock() {
        return Clock.systemUTC();
    }

    @Bean
    public TaskSwitch createTaskSwitch() {
        return () -> {
        };
    }

    @Bean
    @Profile("standalone-dev")
    public Clock createMockServerClock() {
        return new Clock() {
            @Override
            public ZoneId getZone() {
                return null;
            }

            @Override
            public Clock withZone(ZoneId zone) {
                return null;
            }

            @Override
            public Instant instant() {
                try {
                    return clockApi.getClock();
                } catch (Throwable t) {
                    return Instant.now();
                }
            }
        };
    }
}
