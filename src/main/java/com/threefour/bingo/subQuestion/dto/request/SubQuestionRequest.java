package com.threefour.bingo.subQuestion.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class SubQuestionRequest {

    private Long questionId;

    private Map<String, String> subQnA;

}
