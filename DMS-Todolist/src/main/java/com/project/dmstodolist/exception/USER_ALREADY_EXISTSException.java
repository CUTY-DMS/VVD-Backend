package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.exception.BusinessException;
import com.project.dmstodolist.error.ErrorCode;

public class USER_ALREADY_EXISTSException extends BusinessException {

    public USER_ALREADY_EXISTSException() {
        super(ErrorCode.USER_ALREADY_EXISTS);
    }
}
