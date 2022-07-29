package com.project.dmstodolist.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSignInDto {

    @NotNull
    @JsonProperty("account_id")
    private String accountId;

    @NotNull
    private String password;

}
