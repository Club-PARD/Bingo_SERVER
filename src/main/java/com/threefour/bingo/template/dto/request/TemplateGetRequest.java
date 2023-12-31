package com.threefour.bingo.template.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateGetRequest {

    private Long appUserId;

    private Long projectId;

    private Long templateId;

}
