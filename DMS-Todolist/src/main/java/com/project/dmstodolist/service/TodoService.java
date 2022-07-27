package com.project.dmstodolist.service;

import com.project.dmstodolist.dto.request.UpdateTodoRequest;
import com.project.dmstodolist.dto.response.*;
import com.project.dmstodolist.entity.like.Like;
import com.project.dmstodolist.entity.like.LikeRepository;
import com.project.dmstodolist.entity.todolist.Todo;
import com.project.dmstodolist.entity.todolist.TodoRepository;
import com.project.dmstodolist.dto.request.CreateTodoRequestDto;
import com.project.dmstodolist.entity.user.User;
import com.project.dmstodolist.exception.ForbiddenException;
import com.project.dmstodolist.exception.LikeAlreadyExistsException;
import com.project.dmstodolist.exception.TodoListNotFoundException;
import com.project.dmstodolist.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TodoService {

    private final TodoRepository todoRepository;
    private final LikeRepository likeRepository;
    private final UserFacade userFacade;


    @Transactional
    public TodoResponse createTodo(CreateTodoRequestDto request) {

        User user = userFacade.getUser();

        todoRepository.save(Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .dateTime(LocalDateTime.now())
                .completed(false)
                .user(user)
                .build());

        return TodoResponse.builder()
                .message("TodoList : " + request.getTitle() + "을(를) 등록했습니다.")
                .build();
    }


    @Transactional
    public TodoResponse updateTodo(Long id, UpdateTodoRequest request) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        User user = userFacade.getUser();

        todoRepository.save(Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .dateTime(LocalDateTime.now())
                .user(user)
                .build());

        return TodoResponse.builder()
                .message("TodoList : " + request.getTitle() + "을(를) 수정했습니다.")
                .build();
    }


    @Transactional
    public TodoResponse deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        todoRepository.delete(todo);

        return TodoResponse.builder()
                .message("TodoList : " + todo.getTitle() + "을(를) 삭제했습니다.")
                .build();
    }


    @Transactional
    public TodoCheckResponse checkTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        todo.setCompleted(!todo.isCompleted());

        todoRepository.save(todo);

        return TodoCheckResponse.builder()
                .checked(todo.isCompleted())
                .build();
    }

    @Transactional(readOnly = true)
    public MyPageResponse getMyTodo() {

        User user = userFacade.getUser();

        List<TodoDetailResponse> todos = user.getTodos()
                .stream().map(todo -> TodoDetailResponse.builder()
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .name(user.getName())
                        .dateTime(todo.getDateTime())
                        .completed(todo.isCompleted())
                        .isLiked(checkLiked(todo.getId()))
                        .build())
                .collect(Collectors.toList());

        return MyPageResponse.builder()
                .name(user.getName())
                .age(user.getAge())
                .myTodos(todos)
                .build();
    }


    @Transactional(readOnly = true)
    public TodoDetailResponse getTodo(Long id) {

        User user = userFacade.getUser();

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        return TodoDetailResponse.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .name(todo.getUser().getAccountId())
                .dateTime(todo.getDateTime())
                .completed(todo.isCompleted())
                .isLiked(checkLiked(id))
                .build();
    }


    @Transactional(readOnly = true)
    public List<AllTodoResponse> getAllTodo() {

        User user = userFacade.getUser();

        return todoRepository.findAll()
                .stream().map(todo -> {
                    return AllTodoResponse.builder()
                            .title(todo.getTitle())
                            .dateTime(todo.getDateTime())
                            .liked(checkLiked(todo.getId()))
                            .build();
                })
                .collect(Collectors.toList());

    }


    @Transactional
    public LikeResponse addLike(Long id) {

        User user = userFacade.getUser();

        if(likeRepository.findByUserIdAndTodoId(user.getId(), id).isPresent()) {
            throw new LikeAlreadyExistsException();
        }

        likeRepository.save(Like.builder()
                .user(user)
                .todo(todoRepository.findById(id).orElseThrow(TodoListNotFoundException::new))
                .build());

        return LikeResponse.builder()
                .liked(true)
                .build();

    }


    @Transactional
    public LikeResponse removeLike(Long id) {

        User user = userFacade.getUser();

        likeRepository.deleteByUserAndTodo(
                user, todoRepository.findById(id)
                        .orElseThrow(TodoListNotFoundException::new));

        return LikeResponse.builder()
                .liked(false)
                .build();

    }


    public boolean checkLiked(Long id) {

        User user = userFacade.getUser();

        return likeRepository.findByUserIdAndTodoId(user.getId(), id).isPresent();
    }

}