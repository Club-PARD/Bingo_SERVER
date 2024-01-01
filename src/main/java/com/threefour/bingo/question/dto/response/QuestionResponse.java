package com.threefour.bingo.question.dto.response;

import com.threefour.bingo.question.domain.Question;
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
    private List<SubQuestionResponse> subQuestions;

    public QuestionResponse(Question question) {

        this.id = question.getId();
        this.mainQuestion = question.getMainQuestion();
        this.subQuestions = question.getSubQuestionList().stream()
                .map(SubQuestionResponse::new)
                .collect(Collectors.toList());
        log.info("size of subQuestions: {}", question.getSubQuestionList().size());
        log.info("id of mainQuestion: {}", question.getId());
        log.info(question.getMainQuestion());
    }

}