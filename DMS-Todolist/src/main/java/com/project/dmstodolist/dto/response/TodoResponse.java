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
public class TodoResponse {

    private String title;

    private String content;

    private String name;

    private LocalDateTime dateTime;

    private boolean completed;

    private boolean liked;

}
