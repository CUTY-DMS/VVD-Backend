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
    public MessageResponse createTodo(CreateTodoRequestDto request) {

        User user = userFacade.getUser();

        todoRepository.save(Todo.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .dateTime(LocalDateTime.now())
                .completed(false)
                .user(user)
                .build());

        return MessageResponse.builder()
                .message("TodoList : " + request.getTitle() + "을(를) 등록했습니다.")
                .build();
    }


    @Transactional
    public MessageResponse updateTodo(Long id, UpdateTodoRequest request) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        checkUser(todo);

       todo.update(request.getTitle(), request.getContent());
       todoRepository.save(todo);

        return MessageResponse.builder()
                .message("TodoList : " + request.getTitle() + "을(를) 수정했습니다.")
                .build();
    }


    @Transactional
    public MessageResponse deleteTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        checkUser(todo);

        todoRepository.delete(todo);

        return MessageResponse.builder()
                .message("TodoList : " + todo.getTitle() + "을(를) 삭제했습니다.")
                .build();
    }


    @Transactional
    public TodoCheckResponse checkTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        checkUser(todo);

        todo.setCompleted(!todo.isCompleted());

        todoRepository.save(todo);

        return TodoCheckResponse.builder()
                .checked(todo.isCompleted())
                .build();
    }


    private void checkUser(Todo todo) {
        User user = userFacade.getUser();

        if(!todo.getUser().equals(user)) {
            throw new ForbiddenException();
        }
    }


    @Transactional(readOnly = true)
    public MyPageResponse getMyTodo() {

        User user = userFacade.getUser();

        List<TodoDetailResponse> todos = user.getTodos()
                .stream().map(todo -> TodoDetailResponse.builder()
                        .todoId(todo.getId())
                        .title(todo.getTitle())
                        .content(todo.getContent())
                        .name(todo.getUser().getAccountId())
                        .dateTime(todo.getDateTime())
                        .completed(todo.isCompleted())
                        .liked(checkLiked(todo.getId()))
                        .build())
                .collect(Collectors.toList());

        return MyPageResponse.builder()
                .name(user.getAccountId())
                .age(user.getAge())
                .myTodos(todos)
                .build();
    }


    @Transactional(readOnly = true)
    public TodoResponse getTodo(Long id) {

        Todo todo = todoRepository.findById(id)
                .orElseThrow(TodoListNotFoundException::new);

        return TodoResponse.builder()
                .title(todo.getTitle())
                .content(todo.getContent())
                .name(todo.getUser().getAccountId())
                .dateTime(todo.getDateTime())
                .completed(todo.isCompleted())
                .liked(checkLiked(id))
                .build();
    }


    @Transactional(readOnly = true)
    public List<AllTodoResponse> getAllTodo() {

        return todoRepository.findAll()
                .stream().map(todo -> {
                    return AllTodoResponse.builder()
                            .todoId(todo.getId())
                            .title(todo.getTitle())
                            .dateTime(todo.getDateTime())
                            .userName(todo.getUser().getAccountId())
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