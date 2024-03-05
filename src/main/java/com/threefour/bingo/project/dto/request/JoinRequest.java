package com.threefour.bingo.project.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class JoinRequest {

    private Long projectId;

    private String code;
}
