package com.project.dmstodolist.entity.like;

import com.project.dmstodolist.entity.todolist.Todo;
import com.project.dmstodolist.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

    Optional<Like> findByUserIdAndTodoId(Long userId, Long todoId);

    void deleteByUserAndTodo(User user, Todo todo);

}
