package com.project.dmstodolist.controller;

import com.project.dmstodolist.dto.response.LikeResponse;
import com.project.dmstodolist.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/todolist")
@RestController
public class LikeController {

    private final TodoService todoService;

    @PostMapping ("/like/{todo_id}")
    public LikeResponse addLike(@PathVariable(name = "todo_id") Long id) {
        return todoService.addLike(id);
    }


}
