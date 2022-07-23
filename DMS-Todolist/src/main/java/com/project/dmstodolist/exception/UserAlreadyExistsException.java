package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.exception.BusinessException;
import com.project.dmstodolist.error.ErrorCode;

public class UserAlreadyExistsException extends BusinessException {

    public UserAlreadyExistsException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
