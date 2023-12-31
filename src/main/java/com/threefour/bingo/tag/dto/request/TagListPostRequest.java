package com.threefour.bingo.tag.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TagListPostRequest {

    private Long projectId;

    private List<String> name;

}
