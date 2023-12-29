package com.threefour.bingo.subQuestion.dto;

import com.threefour.bingo.subQuestion.domain.SubQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubQuestionDTO {
    private Long id;
    private String subQuestion;
    private String answer;

    public SubQuestionDTO(SubQuestion subQuestion) {
        this.id = subQuestion.getId();
        this.subQuestion = subQuestion.getSubQuestion();
        this.answer = subQuestion.getAnswer();
    }

}


