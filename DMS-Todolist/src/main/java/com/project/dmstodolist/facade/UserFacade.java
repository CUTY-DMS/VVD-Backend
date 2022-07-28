package com.project.dmstodolist.facade;

import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.entity.user.UserRepository;
import com.project.dmstodolist.exception.AuthenticationNotFoundException;
import com.project.dmstodolist.exception.UserNotFoundException;
import com.project.dmstodolist.security.auth.AuthDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getUser() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!(principal instanceof UserDetails)) {
            throw new AuthenticationNotFoundException();
        }

        return ((AuthDetails)principal).getUser();
    }
    
}
