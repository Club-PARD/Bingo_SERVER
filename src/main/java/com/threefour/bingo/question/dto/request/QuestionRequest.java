package com.threefour.bingo.question.dto.request;

import com.threefour.bingo.subQuestion.dto.request.SubQuestionRequest;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequest {

    private Long templateId;

    private String mainQuestion;

//    private List<SubQuestionDto> subQuestionList;

    private SubQuestionRequest subQuestionRequest;
}
