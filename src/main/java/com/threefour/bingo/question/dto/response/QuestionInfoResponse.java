package com.threefour.bingo.question.dto.response;

import com.threefour.bingo.subQuestion.dto.SubQuestionDTO;
import com.threefour.bingo.subQuestion.dto.response.SubQuestionInfoResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class QuestionInfoResponse {

    private Long id;

    private String mainQuestion;

    private List<SubQuestionInfoResponse> subQuestionList = new ArrayList<>();

}
