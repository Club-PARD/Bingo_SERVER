package com.threefour.bingo.question.dto;

import com.threefour.bingo.question.domain.Question;
import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionDTO {

    private Long id;

    private String mainQuestion;

    private List<SubQuestionDTO> subQuestionList = new ArrayList<>();

}

