package com.project.dmstodolist.service;

import com.project.dmstodolist.controller.dto.request.UserSignInDto;
import com.project.dmstodolist.controller.dto.response.TokenResponse;
import com.project.dmstodolist.domain.user.User;
import com.project.dmstodolist.domain.user.UserRepository;
import com.project.dmstodolist.controller.dto.request.UserSignUpDto;
import com.project.dmstodolist.controller.dto.response.UserResponse;
import com.project.dmstodolist.exception.InvalidPasswordException;
import com.project.dmstodolist.exception.USER_ALREADY_EXISTSException;
import com.project.dmstodolist.exception.UserNotFoundException;
import com.project.dmstodolist.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;
    @Transactional
    public UserResponse join(UserSignUpDto userSignUpDto) {
        if(userRepository.existsByAccountId(userSignUpDto.getAccountId())) {
            throw new USER_ALREADY_EXISTSException();
        }
        userRepository.save(User.builder()
                .accountId(userSignUpDto.getAccountId())
                .password(passwordEncoder.encode(userSignUpDto.getPassword()))
                .name(userSignUpDto.getName())
                .age(userSignUpDto.getAge())
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return UserResponse.builder()
                .message("회원가입 성공")
                .name(userSignUpDto.getName())
                .build();
    }


    @Transactional
    public TokenResponse login(UserSignInDto request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new InvalidPasswordException();

        String accessToken = jwtTokenProvider.createToken(user.getAccountId(), user.getRoles());

        return TokenResponse.builder()
                .message("로그인 성공")
                .accessToken(accessToken)
                .build();
    }

    
}
