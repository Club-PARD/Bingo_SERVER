package com.threefour.bingo.question.dto.request;

import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.dto.SubQuestionDto;
import com.threefour.bingo.subQuestion.dto.request.SubQuestionRequest;
import com.threefour.bingo.template.domain.Template;
import lombok.Getter;

import java.util.List;
import java.util.Map;

@Getter
public class QuestionRequest {

    private Long templateId;

    private String mainQuestion;

//    private List<SubQuestionDto> subQuestionList;

    private SubQuestionRequest subQuestionRequest;
}
