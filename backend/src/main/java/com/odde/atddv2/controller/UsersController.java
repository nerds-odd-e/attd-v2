package com.odde.atddv2.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UsersController {

    @PostMapping("login")
    public UserProfile login(@RequestBody UserProfile userProfile) {
        return userProfile;
    }

    @Data
    public static class UserProfile {
        private String userName, password;
    }
}
