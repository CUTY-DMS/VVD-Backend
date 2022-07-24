package com.project.dmstodolist.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AllTodoResponse {

    private String title;

    private LocalDateTime dateTime;

    private boolean liked;
}
