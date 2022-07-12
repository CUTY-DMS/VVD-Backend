package com.project.dmstodolist.domain.user;

import com.project.dmstodolist.domain.todolist.Todo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String accountId;

    private String password;

    private String name;

    private int age;

    @OneToMany(mappedBy = "user")
    private List<Todo> todos;


}



