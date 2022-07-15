package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.exception.BusinessException;
import com.project.dmstodolist.error.ErrorCode;

public class BadRequestException extends BusinessException {
    public BadRequestException() {
        super(ErrorCode.Bad_Request);
    }
}
