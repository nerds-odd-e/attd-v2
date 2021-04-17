package com.odde.atddv2.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "binstd-api", url = "${binstd-endpoint.url}")
public interface BinstdApi {
    @GetMapping("/express/query?type=auto")
    Logistics queryExpress(@RequestParam("appkey") String appkey,
                           @RequestParam("number") String number);

}
