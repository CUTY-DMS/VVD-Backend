package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.exception.BusinessException;
import com.project.dmstodolist.error.ErrorCode;

public class InvalidPasswordException extends BusinessException {
    public InvalidPasswordException() {
        super(ErrorCode.INVALID_PASSWORD);
    }
}
