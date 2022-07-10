package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.exception.BusinessException;
import com.project.dmstodolist.error.ErrorCode;

public class UserNotFoundException extends BusinessException {

    public UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
