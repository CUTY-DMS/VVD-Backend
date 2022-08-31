package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.ErrorCode;
import com.project.dmstodolist.error.exception.BusinessException;

public class InvalidTokenException extends BusinessException {

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
