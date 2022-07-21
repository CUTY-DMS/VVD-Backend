package com.project.dmstodolist.dto.response;

import com.project.dmstodolist.entity.todolist.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class MyPageResponse {

    private String name;

    private int age;

    private List<TodoDetailResponse> myTodos;

}
