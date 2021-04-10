package com.odde.atddv2.controller;

import com.odde.atddv2.entity.User;
import com.odde.atddv2.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletResponse;

import static com.odde.atddv2.config.TokenFilter.Token.makeToken;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    private UserRepo userRepo;

    @PostMapping("login")
    public User login(@RequestBody User user, HttpServletResponse response) {
        User loginUser = userRepo.findByUserNameAndPassword(user.getUserName(), user.getPassword())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        response.setHeader("Token", makeToken(loginUser.getUserName()));
        return loginUser;
    }

}
