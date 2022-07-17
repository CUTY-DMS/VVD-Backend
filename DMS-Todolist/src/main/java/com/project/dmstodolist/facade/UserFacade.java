package com.project.dmstodolist.facade;

import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.entity.user.UserRepository;
import com.project.dmstodolist.exception.TokenInvalidException;
import com.project.dmstodolist.exception.UserNotFoundException;
import com.project.dmstodolist.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getUser() {

        Object detail = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(detail instanceof UserDetails)) {
            throw new TokenInvalidException();
        }

        return userRepository.findByAccountId(((UserDetails) detail).getUsername())
                .orElseThrow(UserNotFoundException::new);
    }
}
