package com.project.dmstodolist.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@AllArgsConstructor
public class UpdateTodoRequest {

    @NotNull
    @Size(min = 1, max = 20, message = "제목은 1이상 20이하")
    private String title;

    @NotNull
    @Size(min = 1, max = 100, message = "내용은 1이상 100이하")
    private String content;

}
