package com.odde.atddv2.logistics.controller;

import com.odde.atddv2.logistics.entity.Express;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/express")
public class ExpressesController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping("query")
    public ResponseEntity<Object> query(@RequestParam String number) {
        Express express = mongoTemplate.findById(number, Express.class);
        if (express == null)
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok(Map.of(
                "status", 0,
                "msg", "ok",
                "result", express
        ));
    }
}
