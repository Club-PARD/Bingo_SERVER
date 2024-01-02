package com.threefour.bingo.tag.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagGetRequest {

    private Long projectId;

    private Long templateId;
}
