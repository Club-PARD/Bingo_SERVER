package com.threefour.bingo.retrospect.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamEvaluationRequest {

    private Long projectId;

    private List<String> name;

}
