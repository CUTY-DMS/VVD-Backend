package com.project.dmstodolist.controller.dto.response;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {

    private String message;

    private String accessToken;
}
