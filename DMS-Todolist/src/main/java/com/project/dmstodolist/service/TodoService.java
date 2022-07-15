package com.project.dmstodolist.service;

import com.project.dmstodolist.dto.request.UpdateTodoRequest;
import com.project.dmstodolist.entity.todolist.Todo;
import com.project.dmstodolist.entity.todolist.TodoRepository;
import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.entity.user.UserRepository;
import com.project.dmstodolist.dto.request.CreateTodoRequestDto;
import com.project.dmstodolist.dto.response.TodoResponseDto;
import com.project.dmstodolist.exception.ForbiddenException;
import com.project.dmstodolist.exception.TodoListNotFoundException;
import com.project.dmstodolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final UserRepository userRepository;


    @Transactional
    public TodoResponseDto createTodo(CreateTodoRequestDto request) {

        todoRepository.save(Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .dateTime(LocalDateTime.now())
                .user(UserFacade.getUser())
                .build());

        return TodoResponseDto.builder()
                .message("TodoList : " + request.getTitle() + "을(를) 등록했습니다.")
                .build();
    }


    @Transactional
    public TodoResponseDto updateTodo(Long id, UpdateTodoRequest request) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        if(todo.getUser() != UserFacade.getUser()) {
            throw new ForbiddenException();
        }

        todoRepository.save(Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .dateTime(LocalDateTime.now())
                .build());

        return TodoResponseDto.builder()
                .message("TodoList : " + request.getTitle() + "을(를) 수정했습니다.")
                .build();
    }







}
