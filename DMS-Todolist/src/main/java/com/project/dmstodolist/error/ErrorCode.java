package com.project.dmstodolist.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    Bad_Request(400, "Bad Request"),
    INVALID_PASSWORD(401, "Invalid Password"),
    FORBIDDEN(403,"Forbidden"),
    USER_NOT_FOUND(404, "User Not Found"),
    TODOLIST_NOT_FOUND(404, "TODOLIST_NOT_FOUND"),
    USER_ALREADY_EXISTS(409, "USER_ALREADY_EXISTS"),

    Internal_Server_Error(500, " Internal Server Error");

    private final int status;

    private final String message;
}

