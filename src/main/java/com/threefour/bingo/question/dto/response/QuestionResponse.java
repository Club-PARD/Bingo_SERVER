package com.threefour.bingo.question.dto.response;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.subQuestion.dto.response.SubQuestionInfoResponse;
import com.threefour.bingo.subQuestion.dto.response.SubQuestionResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class QuestionResponse {

    private Long id;
    private String mainQuestion;
    private List<SubQuestionInfoResponse> subQuestions;

}