package com.threefour.bingo.subQuestion.dto.response;

import com.threefour.bingo.answer.dto.response.AnswerResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubQuestionInfoResponse {

    private Long id;

    private String subQuestion;

    public SubQuestionInfoResponse(SubQuestion subQuestion) {
        this.id = subQuestion.getId();
        this.subQuestion = subQuestion.getSubQuestion();
    }

    public SubQuestion toEntity() {
        return SubQuestion.builder()
                .subQuestion(subQuestion)
                .build();
    }

}
