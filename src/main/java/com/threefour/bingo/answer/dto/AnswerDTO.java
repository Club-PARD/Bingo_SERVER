package com.threefour.bingo.answer.dto;

import com.threefour.bingo.answer.domain.Answer;
import com.threefour.bingo.appUser.domain.AppUser;
import com.threefour.bingo.subQuestion.domain.SubQuestion;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AnswerDTO {

    private Long id;

//    private AppUser appUser;

    private SubQuestionDTO subQuestionDTO;

    private String ans;

    public AnswerDTO(String ans, SubQuestionDTO subQuestion) {
        this.ans = ans;
        this.subQuestionDTO = subQuestion;
    }

    public Answer toEntity() {
        return Answer.builder()
                .ans(ans)
                .subQuestion(subQuestionDTO.toEntity())
                .build();
    }
}
