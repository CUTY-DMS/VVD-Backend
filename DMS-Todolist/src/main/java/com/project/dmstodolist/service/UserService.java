package com.project.dmstodolist.service;

import com.project.dmstodolist.entity.user.Role;
import com.project.dmstodolist.dto.request.UserSignInDto;
import com.project.dmstodolist.dto.response.TokenResponse;
import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.entity.user.UserRepository;
import com.project.dmstodolist.dto.request.UserSignUpDto;
import com.project.dmstodolist.dto.response.MessageResponse;
import com.project.dmstodolist.exception.InvalidPasswordException;
import com.project.dmstodolist.exception.UserAlreadyExistsException;
import com.project.dmstodolist.exception.UserNotFoundException;
import com.project.dmstodolist.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;


    @Transactional
    public MessageResponse join(UserSignUpDto request) {
        if(userRepository.existsByAccountId(request.getAccountId())) {
            throw new UserAlreadyExistsException();
        }
        userRepository.save(User.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .age(request.getAge())
                .role(Role.ROLE_USER)
                .build());

        return MessageResponse.builder()
                .message(request.getName() + "님 회원가입 성공")
                .build();
    }


    @Transactional
    public TokenResponse login(UserSignInDto request) {
        User user = userRepository.findByAccountId(request.getAccountId())
                .orElseThrow(UserNotFoundException::new);

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new InvalidPasswordException();

        String accessToken = jwtTokenProvider.createToken(user.getAccountId());

        return TokenResponse.builder()
                .accessToken(accessToken)
                .build();
    }


}
