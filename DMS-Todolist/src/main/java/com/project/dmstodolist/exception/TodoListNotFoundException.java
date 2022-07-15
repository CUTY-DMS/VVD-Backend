package com.project.dmstodolist.exception;

import com.project.dmstodolist.error.ErrorCode;
import com.project.dmstodolist.error.exception.BusinessException;

public class TodoListNotFoundException extends BusinessException {

    public TodoListNotFoundException() {
        super(ErrorCode.TODOLIST_NOT_FOUND);
    }

}
