package com.threefour.bingo.tag.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TagListProjectRequest {

    private Long projectId;

    private List<String> name;

}
