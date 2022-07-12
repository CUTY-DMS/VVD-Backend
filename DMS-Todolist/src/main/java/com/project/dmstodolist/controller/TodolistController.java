package com.project.dmstodolist.controller;

import com.project.dmstodolist.dto.request.CreateTodoRequestDto;
import com.project.dmstodolist.dto.response.CreateTodoResponseDto;
import com.project.dmstodolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/todolist")
@RestController
public class TodolistController {

    private final TodoService todoService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public CreateTodoResponseDto createTodolist(@Valid @RequestBody CreateTodoRequestDto createTodoRequestDto) {
        return todoService.createTodo(createTodoRequestDto);
    }


    @GetMapping("/search/{id}")
    public void getTodoList(@PathVariable Long id) {

    }



}


