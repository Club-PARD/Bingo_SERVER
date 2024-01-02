package com.threefour.bingo.subQuestion.dto.response;

import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.answer.dto.response.AnswerResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubQuestionResponse {

    private Long id;
    private String subQuestion;
    private AnswerResponse answer;

    public SubQuestionResponse(SubQuestion subQuestion) {
        this.id = subQuestion.getId();
        this.subQuestion = subQuestion.getSubQuestion();
        this.answer = new AnswerResponse(subQuestion.getAnswer());
    }
}
