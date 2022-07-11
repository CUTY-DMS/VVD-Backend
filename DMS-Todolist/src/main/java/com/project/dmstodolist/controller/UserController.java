package com.project.dmstodolist.controller;

import com.project.dmstodolist.controller.dto.request.UserSignInDto;
import com.project.dmstodolist.controller.dto.request.UserSignUpDto;
import com.project.dmstodolist.controller.dto.response.TokenResponse;
import com.project.dmstodolist.controller.dto.response.UserResponse;
import com.project.dmstodolist.security.JwtTokenProvider;
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
    public UserResponse signup(@Valid @RequestBody UserSignUpDto userSignUpDto) {
        return userService.join(userSignUpDto);
    }


    @PostMapping("/signin")
    public TokenResponse login(@Valid @RequestBody UserSignInDto userSignInDto) {
        return userService.login(userSignInDto);
    }

}

