package com.project.dmstodolist.controller;

import com.project.dmstodolist.dto.request.UserSignInDto;
import com.project.dmstodolist.dto.request.UserSignUpDto;
import com.project.dmstodolist.dto.response.TokenResponse;
import com.project.dmstodolist.dto.response.MessageResponse;
import com.project.dmstodolist.security.jwt.JwtTokenProvider;
import com.project.dmstodolist.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse signup(@Valid @RequestBody UserSignUpDto request) {
        return userService.join(request);
    }


    @PostMapping("/signin")
    public TokenResponse login(@Valid @RequestBody UserSignInDto request) {
        return userService.login(request);
    }

}

