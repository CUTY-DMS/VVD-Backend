package com.project.dmstodolist.facade;

import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.security.auth.AuthDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null || authentication.getPrincipal() == null
                || !((authentication.getPrincipal()) instanceof AuthDetails))
            return null;


        return (User) authentication.getPrincipal();
    }
}
