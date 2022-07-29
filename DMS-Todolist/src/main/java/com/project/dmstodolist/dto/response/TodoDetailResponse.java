package com.project.dmstodolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Getter
public class TodoDetailResponse {

    private Long todoId;

    private String title;

    private String content;

    private String name;

    private LocalDateTime dateTime;

    private boolean completed;

    private boolean liked;

}
