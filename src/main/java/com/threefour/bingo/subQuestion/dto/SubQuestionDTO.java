package com.threefour.bingo.subQuestion.dto;

import com.threefour.bingo.answer.dto.response.AnswerResponse;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Slf4j
public class SubQuestionDTO {

    private Long id;

    private String subQuestion;

    private List<AnswerResponse> answerResponses;

    public SubQuestionDTO(SubQuestion subQuestion) {
        this.id = subQuestion.getId();
        this.subQuestion = subQuestion.getSubQuestion();
        if (subQuestion.getAnswerList() != null) {
            this.answerResponses = subQuestion.getAnswerList().stream()
                    .map(answer -> new AnswerResponse(answer))
                    .collect(Collectors.toList());
         } else {
            this.answerResponses = new ArrayList<>();
        }
    }

    public SubQuestion toEntity() {
        return SubQuestion.builder()
                .subQuestion(subQuestion)
                .build();
    }
}
