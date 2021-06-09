package com.odde.atddv2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringVueApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringVueApplication.class, args);
    }

}

