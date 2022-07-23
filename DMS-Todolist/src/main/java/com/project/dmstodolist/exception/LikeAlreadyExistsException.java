package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.ErrorCode;
import com.project.dmstodolist.error.exception.BusinessException;

public class LikeAlreadyExistsException extends BusinessException {

    public LikeAlreadyExistsException() {
        super(ErrorCode.LIKE_ALREADY_EXISTS);
    }
}
