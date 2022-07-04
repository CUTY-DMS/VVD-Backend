package com.project.dmstodolist.dto.memberdto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class MemberSignUpDto {

    @NotNull
    private String account_id;

    @NotNull
    private String password;

    @NotNull
    private String name;

    private int age;


}
