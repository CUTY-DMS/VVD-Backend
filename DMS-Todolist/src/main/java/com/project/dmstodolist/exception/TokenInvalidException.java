package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.ErrorCode;
import com.project.dmstodolist.error.exception.BusinessException;

public class TokenInvalidException extends BusinessException {

    public TokenInvalidException() {
        super(ErrorCode.TOKEN_INVALID);
    }
}
