package com.threefour.bingo.subQuestion.dto.response;

import com.threefour.bingo.answer.dto.response.AnswerResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SubQuestionResponse {

    private Long id;
    private String subQuestion;
    private List<AnswerResponse> answer;

    public SubQuestionResponse(SubQuestion subQuestion) {
        this.id = subQuestion.getId();
        this.subQuestion = subQuestion.getSubQuestion();
        if (subQuestion.getAnswerList() != null) {
            this.answer = subQuestion.getAnswerList().stream()
                    .map(answer -> new AnswerResponse(answer))
                    .collect(Collectors.toList());
        }
    }
}
