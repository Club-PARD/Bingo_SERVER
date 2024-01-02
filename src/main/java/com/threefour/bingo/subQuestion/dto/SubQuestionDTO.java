package com.threefour.bingo.subQuestion.dto;

import com.threefour.bingo.answer.dto.AnswerDTO;
import com.threefour.bingo.answer.dto.response.AnswerResponse;
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
    private AnswerResponse answerResponse;

    public SubQuestionDTO(SubQuestion subQuestion) {
        this.id = subQuestion.getId();
        this.subQuestion = subQuestion.getSubQuestion();
        this.answerResponse = new AnswerResponse(subQuestion.getAnswer().getId(), subQuestion.getAnswer().getAns());
    }

    public SubQuestionDTO(SubQuestionDTO subQuestionDTO) {
        this.id = subQuestionDTO.getId();
        this.subQuestion = subQuestionDTO.getSubQuestion();
        this.answerResponse = subQuestionDTO.getAnswerResponse();
    }

    public SubQuestion toEntity() {
        return SubQuestion.builder()
                .subQuestion(subQuestion)
                .build();
    }
}


