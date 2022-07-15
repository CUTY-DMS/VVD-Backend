package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.ErrorCode;
import com.project.dmstodolist.error.exception.BusinessException;

public class ForbiddenException extends BusinessException {

    public ForbiddenException() {
        super(ErrorCode.FORBIDDEN);
    }
}
