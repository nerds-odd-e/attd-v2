package com.odde.atddv2.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

@FeignClient(name = "clock-api", url = "${clock-endpoint.url}")
public interface ClockApi {

    @GetMapping("/clock")
    Instant getClock();

}
