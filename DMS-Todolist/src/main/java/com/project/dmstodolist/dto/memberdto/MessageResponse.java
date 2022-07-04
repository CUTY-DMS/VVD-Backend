package com.project.dmstodolist.dto.memberdto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MessageResponse {
    private String message;

    private String name;
}
