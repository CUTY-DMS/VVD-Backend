package com.project.dmstodolist.error;

import com.project.dmstodolist.error.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ExceptionResponse> handlerException(BusinessException e) {
        final ErrorCode errorCode = e.getErrorCode();

        return new ResponseEntity<>(new ExceptionResponse(errorCode.getStatus(),
                errorCode.getMessage()), HttpStatus.valueOf(errorCode.getStatus()));
    }
}
