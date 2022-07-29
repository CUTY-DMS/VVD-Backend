package com.project.dmstodolist.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class AllTodoResponse {

    private Long todoId;

    private String title;

    private LocalDateTime dateTime;

    private String userName;
}
