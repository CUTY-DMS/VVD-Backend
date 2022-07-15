package com.project.dmstodolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UpdateTodoRequest {

    private String title;

    private String content;

}
