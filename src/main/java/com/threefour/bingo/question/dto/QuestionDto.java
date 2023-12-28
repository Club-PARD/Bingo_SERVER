package com.threefour.bingo.question.dto;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.subQuestion.dto.SubQuestionDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionDto {

    private Long id;

    private String mainQuestion;

    private List<SubQuestionDto> subQuestionList = new ArrayList<>();

    public QuestionDto(Question question) {

        this.id = question.getId();

        this.mainQuestion = question.getMainQuestion();

        this.subQuestionList = question
                .getSubQuestionList()
                .stream()
                .map(SubQuestionDto::new)
                .collect(Collectors.toList());
    }
}
