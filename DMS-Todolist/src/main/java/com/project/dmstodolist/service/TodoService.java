package com.project.dmstodolist.service;

import com.project.dmstodolist.domain.todolist.Todo;
import com.project.dmstodolist.domain.todolist.TodoRepository;
import com.project.dmstodolist.dto.request.CreateTodoRequestDto;
import com.project.dmstodolist.dto.response.CreateTodoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;

    @Transactional
    public CreateTodoResponseDto createTodo(CreateTodoRequestDto createTodoRequestDto) {
        todoRepository.save(Todo.builder()
                .title(createTodoRequestDto.getTitle())
                .content(createTodoRequestDto.getContent())
                .build());
        return CreateTodoResponseDto.builder()
                .message(createTodoRequestDto.getTitle() + " 등록 완료")
                .build();
    }


}
