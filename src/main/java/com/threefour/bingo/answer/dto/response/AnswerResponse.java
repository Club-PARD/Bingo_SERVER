package com.threefour.bingo.answer.dto.response;

import com.threefour.bingo.answer.domain.Answer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AnswerResponse {
    private Long id;
    private String ams;

    public AnswerResponse(Answer answer) {
        this.id = answer.getId();
        this.ams = answer.getAns();
    }
}
