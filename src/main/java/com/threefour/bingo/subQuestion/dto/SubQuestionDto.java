package com.threefour.bingo.subQuestion.dto;

import com.threefour.bingo.subQuestion.domain.SubQuestion;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SubQuestionDto {
    private Long id;

    private String subQuestion;

    private String answer;

    public SubQuestionDto(SubQuestion subQuestion) {

        this.id = subQuestion.getId();

        this.subQuestion = subQuestion.getSubQuestion();

        this.answer = subQuestion.getAnswer();

    }
}
