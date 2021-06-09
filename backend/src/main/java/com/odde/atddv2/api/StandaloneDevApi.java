package com.odde.atddv2.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.Instant;

@FeignClient(name = "standalone-dev-api", url = "${standalone-dev-endpoint.url}")
public interface StandaloneDevApi {

    @GetMapping("/clock")
    Instant getClock();

    @GetMapping("/task")
    boolean isGo();

}
