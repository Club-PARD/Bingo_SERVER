package com.threefour.bingo.template.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TemplateGetAllRequest {

    private Long appUserId;

    private Long projectId;

}
