package com.project.dmstodolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
@Builder
public class MyPageResponse {

    private String name;

    private int age;

    private List<TodoDetailResponse> myTodos;

}
