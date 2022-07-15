package com.project.dmstodolist.controller;

import com.project.dmstodolist.dto.request.UpdateTodoRequest;
import com.project.dmstodolist.dto.request.CreateTodoRequestDto;
import com.project.dmstodolist.dto.response.TodoResponseDto;
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
    public TodoResponseDto createTodo(@Valid @RequestBody CreateTodoRequestDto request) {
        return todoService.createTodo(request);
    }

    @PatchMapping ("/{todo_id}")
    public TodoResponseDto updateTodo(@PathVariable("todo_id") Long id, @Valid @RequestBody UpdateTodoRequest request) {
        return todoService.updateTodo(id, request);
    }

    @DeleteMapping("{todo_id}")
    public TodoResponseDto deleteTodo(@PathVariable("todo_id") Long id) {
        return todoService.deleteTodo(id);
    }






}


