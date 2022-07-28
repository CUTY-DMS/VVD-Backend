package com.project.dmstodolist.facade;

import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.exception.AuthenticationNotFoundException;
import com.project.dmstodolist.security.auth.AuthDetails;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    public User getUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserDetails)) {
            throw new AuthenticationNotFoundException();
        }

        return ((AuthDetails)principal).getUser();
    }

}
