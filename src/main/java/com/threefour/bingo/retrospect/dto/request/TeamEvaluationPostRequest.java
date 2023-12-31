package com.threefour.bingo.retrospect.dto.request;

import com.threefour.bingo.tag.dto.TagDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class TeamEvaluationPostRequest {

    private Long projectId;

    private List<TagDTO> tagList;

}
