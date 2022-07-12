package com.project.dmstodolist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSignUpDto {

    @NotNull
    @Size(max = 20, message = "아이디는 최대 20글자")
    @JsonProperty("account_id")
    private String accountId;

    @NotNull
    @Size(min = 8, max = 20, message = "비밀번호는 최소 8글자 최대 20글자")
    private String password;

    @NotNull
    @Size(max = 10, message = "이름은는 최대 10글자")
    private String name;

    @NotNull
    private int age;

}
