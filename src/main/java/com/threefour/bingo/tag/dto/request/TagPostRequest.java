package com.threefour.bingo.tag.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagPostRequest {

    private Long id;

    private Integer selected;

}
