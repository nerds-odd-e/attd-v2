package com.odde.atddv2.config;

import com.odde.atddv2.api.StandaloneDevApi;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

@Configuration
@ConditionalOnMissingBean({Clock.class, TaskSwitch.class})
public class Factory {

    @Autowired
    StandaloneDevApi standaloneDevApi;

    @Bean
    @Profile("!standalone-dev")
    public Clock createClock() {
        return Clock.systemUTC();
    }

    @Bean
    @Profile("!standalone-dev")
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
                    return standaloneDevApi.getClock();
                } catch (Throwable t) {
                    return Instant.now();
                }
            }
        };
    }

    @Bean
    @Profile("standalone-dev")
    public TaskSwitch createMockServerTaskSwitch() {
        return new TaskSwitch() {
            @SneakyThrows
            @Override
            public void waitForExecute() {
                while (isNotGo()) {
                    TimeUnit.MILLISECONDS.sleep(100);
                }
            }

            private boolean isNotGo() {
                try {
                    return !standaloneDevApi.isGo();
                } catch (Throwable t) {
                    return true;
                }
            }
        };
    }
}
