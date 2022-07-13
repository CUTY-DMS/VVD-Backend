package com.project.dmstodolist.facade;

import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.exception.UserNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public User getUserId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null) {
            throw new UserNotFoundException();
        }

        User user = (User) authentication.getPrincipal();

        return user;
    }
}
