package com.project.dmstodolist.dto.request;

import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSignInDto {

    @NotNull
    private String accountId;

    @NotNull
    private String password;

}
