package com.project.dmstodolist.service;

import com.project.dmstodolist.entity.todolist.Todo;
import com.project.dmstodolist.entity.todolist.TodoRepository;
import com.project.dmstodolist.entity.user.UserRepository;
import com.project.dmstodolist.dto.request.CreateTodoRequestDto;
import com.project.dmstodolist.dto.response.CreateTodoResponseDto;
import com.project.dmstodolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    @Transactional
    public CreateTodoResponseDto createTodo(CreateTodoRequestDto createTodoRequestDto) {

        todoRepository.save(Todo.builder()
                .title(createTodoRequestDto.getTitle())
                .content(createTodoRequestDto.getContent())
                .dateTime(LocalDateTime.now())
                .user(UserFacade.getUser())
                .build());

        return CreateTodoResponseDto.builder()
                .message("TodoList : " + createTodoRequestDto.getTitle() + "을(를) 등록했습니다.")
                .build();
    }



}
