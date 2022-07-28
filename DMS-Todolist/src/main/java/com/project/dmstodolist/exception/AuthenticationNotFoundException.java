package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.ErrorCode;
import com.project.dmstodolist.error.exception.BusinessException;

public class AuthenticationNotFoundException extends BusinessException {

    public AuthenticationNotFoundException() {
        super(ErrorCode.AUTHENTICATION_NOT_FOUND);
    }
}
